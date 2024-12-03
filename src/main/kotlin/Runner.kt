package com.lukas

import com.lukas.solutions.Day
import com.lukas.solutions.Input
import com.lukas.solutions.Solution
import java.io.File

private object Context {
    const val InputPath = "input/"
}

val inputs by lazy {
    Context.javaClass.classLoader
        .getResource(Context.InputPath)
        ?.toURI()
        ?.let(::File)
        ?.listFiles()
        ?.toList()
        ?.map(Input::fromURLIdent) ?: error("Failed to read input from resources.")
}

val solutions: List<Solution> = Solution.all()

inline fun run(
    selector: (Solution) -> Boolean = { true }
) = solutions
    .sortedBy { it.date }
    .filter(selector).map { solution ->
        solution to inputs.first { input -> solution.date.day == input.day }
    }.forEach { (solution, input) -> solution(input) }

fun runFor(day: Day) = run { it.date.day == day }
