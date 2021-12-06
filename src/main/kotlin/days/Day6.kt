package days

class Day6 : Day(6) {

    override fun partOne(): Any {
        return solve(80)
    }

    override fun partTwo(): Any {
        return solve(256)
    }

    private fun solve(n: Int): Long {
        val input = inputString.split(",").map { it.toInt() }
        var map = input.groupBy { it }.mapValues { it.value.size.toLong() }.toMutableMap()

        (0 until n).forEach { _ ->
            map = map.mapKeys { it.key - 1 }.toMutableMap()
            val count = map.remove(-1) ?: 0
            map[6] = count + (map[6] ?: 0)
            map[8] = count
        }
        return map.values.sum()
    }
}
