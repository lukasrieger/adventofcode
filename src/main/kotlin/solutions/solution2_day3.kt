package com.lukas.solutions


data object Solution2Day3 : Solution {
    override val date = SolutionDate(Day.Day3, 2)
    override fun solve(input: Input) =
        Solution1Day3Impl.solve(input.file, extendedInstructionSet = true)
}