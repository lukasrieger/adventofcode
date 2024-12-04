package com.lukas.solutions

import java.io.File

data object Solution2Day1 : Solution {
    override val date = SolutionDate(Day.Day1, 2)
    override fun solve(input: Input) = Solution2Day1Impl.solve(input.file)
}

private object Solution2Day1Impl {
    private fun solve(first: Set<Int>, second: List<Int>): Int {
        val frequencyMap = second.fold(mutableMapOf<Int, Int>()) { acc, key ->
            acc.merge(key, 1, Int::plus)
            acc
        }

        return first.sumOf { v -> frequencyMap.getOrDefault(v, 0) * v }
    }

    private fun parse(input: File): Pair<Set<Int>, List<Int>> =
        input.readLines().map {
            it.substringBefore(' ').trim().toInt() to
                    it.substringAfter(' ').trim().toInt()
        }.unzip().let { (first, second) -> first.toSet() to second }

    fun solve(input: File): Int =
        parse(input).let { (first, second) ->
            solve(first, second)
        }
}