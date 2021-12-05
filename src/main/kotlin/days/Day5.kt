package days

import kotlin.math.abs
import kotlin.math.max

class Day5 : Day(5) {

    private fun parseInput() = inputList.map { it.split(" -> ").map { it.split(",").map { it.toInt() } } }

    override fun partOne(): Any {
        val input = parseInput()
        val xyInput = input.filter { (a, b) -> a[0] == b[0] || a[1] == b[1] }
        return matrixCount(input, xyInput)
    }

    override fun partTwo(): Any {
        val input = parseInput()
        val xyInput = input.filter { (a, b) -> a[0] == b[0] || a[1] == b[1] || abs(a[0] - b[0]) == abs(a[1] - b[1]) }
        return matrixCount(input, xyInput)
    }

    private fun matrixCount(
        input: List<List<List<Int>>>,
        xyInput: List<List<List<Int>>>
    ): Int {
        val size = input.flatten().flatten().maxOrNull() ?: -1
        val matrix = Array(size + 1) { IntArray(size + 1) { 0 } }
        xyInput.forEach { (a, b) ->
            val dx = b[0] - a[0]
            val dy = b[1] - a[1]
            val ix = if (dx > 0) 1 else if (dx < 0) -1 else 0
            val iy = if (dy > 0) 1 else if (dy < 0) -1 else 0
            (0..max(abs(dx), abs(dy))).forEach {
                matrix[a[1] + it * iy][a[0] + it * ix] += 1
            }
        }

        return matrix.map { it.toList() }.flatten().count{ it >= 2 }
    }
}
