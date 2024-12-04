package com.lukas.solutions

import java.io.File
import kotlin.Int
import kotlin.String
import kotlin.let
import kotlin.to
import kotlin.collections.sum as intSum


data object Solution1Day2 : Solution {
    override val date = SolutionDate(Day.Day2, 1)
    override fun solve(input: Input): String = Solution1Day2Impl.solve(input.file, Solution1Day2Impl::solve).toString()
}

private enum class Mode {
    Initial, Increasing, Decreasing
}

private enum class Result {
    Safe, Unsafe
}

internal object Solution1Day2Impl {
    fun solve(input: List<List<Int>>): Int =
        (input.map { level: List<Int> ->
            level.zipWithNext().fold(Mode.Initial to Result.Safe) { (mode, result), (current, next) ->
                if (result == Result.Unsafe) return@fold (mode to result)

                val newMode = current.compareTo(next).let {
                    when {
                        it == 0 -> Mode.Initial
                        it < 0 -> Mode.Increasing
                        else -> Mode.Decreasing
                    }
                }
                val withinRange = kotlin.math.abs(current - next) in 1..3

                when (mode) {
                    Mode.Initial -> if (withinRange && newMode != mode) newMode to Result.Safe else mode to Result.Unsafe
                    Mode.Increasing -> if (withinRange && newMode == Mode.Increasing) mode to Result.Safe else mode to Result.Unsafe
                    Mode.Decreasing -> if (withinRange && newMode == Mode.Decreasing) mode to Result.Safe else mode to Result.Unsafe
                }
            }.let { (_, result) -> if (result == Result.Unsafe) 0 else 1 }
        }.intSum())

    private fun parse(input: File): List<List<Int>> =
        input.readLines().map {
            it.split(" ").map(String::toInt)
        }

    fun solve(input: File, solver: (List<List<Int>>) -> Int) = solver(parse(input))

}