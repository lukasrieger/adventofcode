package com.lukas.solutions

import java.io.File
import kotlin.math.abs


data object Solution1Day1 : Solution {
    override val date = SolutionDate(Day.Day1, 1)
    override fun solve(input: Input) = Solution1Day1Impl.solve(input.file)
}

object Solution1Day1Impl {
    private fun solve(first: List<Int>, second: List<Int>): Int =
        (first.sorted() zip second.sorted())
            .sumOf { (a, b) -> abs(a - b) }

    private fun parse(input: File): Pair<List<Int>, List<Int>> =
        input.readLines().map {
            it.substringBefore(' ').trim().toInt() to
                    it.substringAfter(' ').trim().toInt()
        }.unzip()

    internal fun solve(input: File): Int =
        parse(input).let { (first, second) ->
            solve(first, second)
        }
}
