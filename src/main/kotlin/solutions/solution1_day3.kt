package com.lukas.solutions

import java.io.File


data object Solution1Day3 : Solution {
    override val date = SolutionDate(Day.Day3, 1)
    override fun solve(input: Input) = Solution1Day3Impl.solve(
        input.file, extendedInstructionSet = false
    )
}

private sealed interface Instruction {
    data object Enable : Instruction
    data object Disable : Instruction
    data class Mul(val left: Int, val right: Int) : Instruction
}

private data class State(
    val result: Int,
    val active: Boolean
) {
    companion object {
        val Initial = State(0, true)
    }
}

internal object Solution1Day3Impl {

    private val instrPattern = Regex("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)|(?<enable>do\\(\\))|(?<disable>don't\\(\\))")
    private fun parse(input: File): Sequence<Instruction> =
        input.readText().let { content ->
            instrPattern.findAll(content)
                .map { matchResult ->
                    when {
                        matchResult.groups["enable"] != null -> Instruction.Enable
                        matchResult.groups["disable"] != null -> Instruction.Disable
                        else -> {
                            val (first, second) = matchResult.destructured
                            Instruction.Mul(first.toInt(), second.toInt())
                        }
                    }
                }
        }

    private fun solve(seq: Sequence<Instruction>, extendedInstructionSet: Boolean): Int =
        seq.fold(State.Initial) { state, current ->
            when (current) {
                Instruction.Disable ->
                    state.copy(active = false).takeIf { extendedInstructionSet } ?: state

                Instruction.Enable ->
                    state.copy(active = true).takeIf { extendedInstructionSet } ?: state

                is Instruction.Mul ->
                    if (state.active) state.copy(result = state.result + current.left * current.right) else state
            }
        }.result

    fun solve(input: File, extendedInstructionSet: Boolean) = solve(parse(input), extendedInstructionSet)
}


