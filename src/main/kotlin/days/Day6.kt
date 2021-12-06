package days

class Day6 : Day(6) {

    override fun partOne(): Any {
        val input = inputString.split(",").map { it.toInt() }.toMutableList()

        (0 until 80).forEach { _ ->
            val spawn = input.count { it == 0 }
            input.replaceAll { it - 1 }
            input.replaceAll { if (it == -1) 6 else it }
            input.addAll(IntArray(spawn) { 8 }.toList())
        }
        return input.size
    }

    override fun partTwo(): Any {
        val input = inputString.split(",").map { it.toInt() }
        var map = input.groupBy { it }.mapValues { it.value.size.toLong() }.toMutableMap()

        (0 until 256).forEach { _ ->
            map = map.mapKeys { it.key - 1 }.toMutableMap()
            val count = map.remove(-1) ?: 0
            map[6] = count + (map[6] ?: 0)
            map[8] = count
        }
        return map.values.sum()
    }
}
