package days

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day5 : Day(5) {

    private fun parseInput() = inputList.map { it.split(" -> ").map { it.split(",").map { it.toInt() } } }

    override fun partOne(): Any {
        val input = parseInput()
        val xyInput = input.filter { (a, b) -> a[0] == b[0] || a[1] == b[1] }

        return matrixCount(input, xyInput)
    }

    override fun partTwo(): Any {
        val input = parseInput()
        val xyInput = input.filter { (a, b) -> a[0] == b[0] || a[1] == b[1] }
        val digInput = input.filter { (a, b) -> abs(a[0] - b[0]) == abs(a[1] - b[1]) }

        return matrixCount(input, xyInput, digInput)
    }

    private fun matrixCount(
        input: List<List<List<Int>>>,
        xyInput: List<List<List<Int>>>,
        digInput: List<List<List<Int>>> = listOf()
    ): Int {
        val size = input.flatten().flatten().maxOrNull() ?: -1
        val matrix = Array(size + 1) { IntArray(size + 1) { 0 } }
        xyInput.forEach { (a, b) ->
            (min(a[0], b[0])..max(a[0], b[0])).forEach { x ->
                (min(a[1], b[1])..max(a[1], b[1])).forEach { y ->
                    matrix[y][x] += 1
                }
            }
        }

        digInput.forEach { (a, b) ->
            val dx = if (b[0] > a[0]) 1 else -1
            val dy = if (b[1] > a[1]) 1 else -1
            (0..abs(a[0] - b[0])).forEach {
                matrix[a[1] + it * dy][a[0] + it * dx] += 1
            }
        }

        return matrix.map { it.toList() }.flatten().count{ it >= 2 }
    }
}
