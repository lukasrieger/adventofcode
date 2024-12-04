package com.lukas.solutions


data object Solution2Day2 : Solution {
    override val date = SolutionDate(Day.Day2, 2)
    override fun solve(input: Input) = Solution1Day2Impl.solve(input.file) {
        Solution2Day2Impl.solve(it, Solution1Day2Impl::solve)
    }
}

private object Solution2Day2Impl {
    private fun List<Int>.permuteMissingOne(): List<List<Int>> =
        List(this.size) { index -> this.filterIndexed { i, _ -> i != index } }.plusElement(this)

    fun solve(
        input: List<List<Int>>,
        solver: (List<List<Int>>) -> Int
    ) =
        input.sumOf {
            val permutations = it.permuteMissingOne()
            val result = solver(permutations)
            val r = if (result > 0) 1 else 0
            r
        }
}