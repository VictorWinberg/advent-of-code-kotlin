package days

import kotlin.math.abs

class Day7 : Day(7) {

    override fun partOne(): Any {
        return solveWith(::fuelLin)
    }

    override fun partTwo(): Any {
        return solveWith(::fuelTri)
    }

    private fun solveWith(fn: (Int, List<Int>) -> Int): Int {
        val input = inputString.split(",").map { it.toInt() }
        val max = input.maxOrNull() ?: throw Error("Missing input")
        return (0..max).minOf { value -> fn(value, input) }
    }

    private fun fuelLin(value: Int, input: List<Int>): Int {
        return input.sumOf { abs(it - value) }
    }

    private fun fuelTri(value: Int, input: List<Int>): Int {
        return input.map { abs(it - value) }.sumOf { n -> n * (n + 1) / 2 }
    }
}
