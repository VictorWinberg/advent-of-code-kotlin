package days

import kotlin.math.abs

class Day7 : Day(7) {

    private fun parseInput(): List<Int> = inputString.split(",").map { it.toInt() }

    override fun partOne(): Any {
        return parseInput().minSolve(::fuelLin)
    }

    override fun partTwo(): Any {
        return parseInput().minSolve(::fuelTri)
    }

    private fun List<Int>.minSolve(fn: (Int, List<Int>) -> Int): Int {
        return (minOf { it }..maxOf { it }).minOf { value -> fn(value, this) }
    }

    private fun fuelLin(value: Int, input: List<Int>): Int {
        return input.sumOf { abs(it - value) }
    }

    private fun fuelTri(value: Int, input: List<Int>): Int {
        return input.map { abs(it - value) }.sumOf { n -> n * (n + 1) / 2 }
    }
}
