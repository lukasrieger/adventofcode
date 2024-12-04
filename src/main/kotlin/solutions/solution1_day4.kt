package com.lukas.solutions

import java.io.File

typealias Matrix = Array<CharArray>
typealias Patterns = List<List<Pair<Int, Int>>>


data object Solution1Day4 : Solution {
    override val date: SolutionDate = SolutionDate(Day.Day4, 1)
    override fun solve(input: Input) =
        Solution1Day4Impl.solve(
            matrix = Solution1Day4Impl.parse(input.file),
            marker = 'X',
            patternSelector = Solution1Day4Impl::patterns,
            score = { pat -> pat.count { it == "XMAS" } }
        )
}

internal object Solution1Day4Impl {
    fun patterns(i: Int, k: Int) = listOf(
        (k..k + 3).map { i to it }, (k downTo k - 3).map { i to it },
        (i..i + 3).map { it to k }, (i downTo i - 3).map { it to k },
        (i downTo i - 3).zip(k..k + 3), (i downTo i - 3).zip(k downTo k - 3),
        (i..i + 3).zip(k..k + 3), (i..i + 3).zip(k downTo k - 3)
    )

    private fun Matrix.patternsAt(i: Int, k: Int, patternSelector: (Int, Int) -> Patterns): List<String> =
        patternSelector(i, k).map { pattern ->
            pattern.mapNotNull { (i, k) ->
                this.getOrNull(i)?.getOrNull(k)
            }.joinToString("")
        }

    fun solve(
        matrix: Matrix,
        marker: Char,
        patternSelector: (i: Int, k: Int) -> Patterns,
        score: (List<String>) -> Int
    ): Int =
        matrix.withIndex().sumOf { (i, chars) ->
            chars.withIndex().sumOf { (k, char) ->
                if (char == marker) {
                    score(matrix.patternsAt(i, k, patternSelector))
                } else 0
            }
        }

    fun parse(input: File): Matrix =
        input.readLines().map { it.toCharArray() }.toTypedArray()
}
