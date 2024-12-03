package com.lukas.solutions

import java.io.File

enum class Day {
    Day1, Day2, Day3;

    companion object {
        fun fromIntegral(int: Int): Day = entries[int - 1]
    }
}

data class Input(val file: File, val day: Day) {
    companion object {
        fun fromURLIdent(file: File): Input {
            return Input(
                file = file,
                day = Day.fromIntegral(file.name.dropLast(4).last().digitToInt())
            )
        }
    }
}

data class SolutionDate(val day: Day, val part: Int) : Comparable<SolutionDate> {
    override fun compareTo(other: SolutionDate): Int =
        when {
            this == other -> 0
            day > other.day -> 1
            day < other.day -> -1
            else -> part compareTo other.part
        }
}

sealed interface Solution {
    val date: SolutionDate

    val description: String

    fun solve(input: Input): String

    fun run(input: Input) {
        println("")
        println("Running $description | Result: ${solve(input)}")
    }

    operator fun invoke(input: Input) = run(input)

    companion object {
        fun all() = Solution::class
            .sealedSubclasses
            .map { it.objectInstance as Solution }
    }
}

