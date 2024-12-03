package com.lukas.solutions


data object Solution2Day3 : Solution {
    override val date = SolutionDate(Day.Day3, 2)
    override val description: String = "Solution for AdventOfCode 2024 - Day 3 - Part 2"
    override fun solve(input: Input): String =
        Solution1Day3Impl.solve(input.file, extendedInstructionSet = true).toString()
}