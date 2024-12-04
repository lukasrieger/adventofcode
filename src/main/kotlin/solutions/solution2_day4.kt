package com.lukas.solutions


data object Solution2Day4 : Solution {
    override val date: SolutionDate = SolutionDate(Day.Day4, 2)
    override fun solve(input: Input): String =
        Solution1Day4Impl.solve(
            matrix = Solution1Day4Impl.parse(input.file),
            marker = 'A',
            patternSelector = { i, k ->
                listOf(
                    (i - 1..i + 1).zip(k - 1..k + 1),
                    (i + 1 downTo i - 1).zip(k - 1..k + 1)
                )
            },
            score = { pat -> if (pat.all { it == "MAS" || it == "SAM" }) 1 else 0 }
        ).toString()
}