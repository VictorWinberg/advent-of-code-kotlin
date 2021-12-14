package days

class Day14 : Day(14) {

    override fun partOne(): Any {
        var input = inputTestString.split("\n\n")[0]
        val commands = inputTestString.split("\n\n")[1].split("\n").map { Pair(it.split(" -> ")[0], it.split(" -> ")[1]) }.toMap()

        (0 until 10).forEach {
            input = input.zipWithNext().map { (a, b) ->
                val pair = a.toString() + b.toString()
                val key = commands.keys.find { it == pair } ?: return@map a.toString()
                a.toString() + commands[key]
            }.joinToString("") + input[input.length - 1]
        }

        val map = input.groupBy { it }.mapValues { (_, value) -> value.count() }
        return map.maxOf { it.value } - map.minOf { it.value }
    }

    override fun partTwo(): Any {
        val inputStr = inputString.split("\n\n")[0]
        var input = (inputStr + "_").zipWithNext().groupBy { it.first.toString() + it.second.toString() }.mapValues { (_, v) -> v.size.toLong() }
        val commands = inputString.split("\n\n")[1].split("\n").map { Pair(it.split(" -> ")[0], it.split(" -> ")[1]) }.toMap()

        (0 until 40).forEach {
            val map = input.toMap() as HashMap<String, Long>

            input.forEach poop@{ (pair, count) ->
                val key = commands.keys.find { it == pair } ?: return@poop
                val value = commands[key]!!
                val leftPair = pair[0] + value
                val rightPair = value + pair[1]
                map[pair] = map[pair]!!.minus(count)
                map[leftPair] = map.getOrDefault(leftPair, 0) + count
                map[rightPair] = map.getOrDefault(rightPair, 0) + count
            }

            input = map.toMap()
        }

        val map2 = input.map { (k, v) -> k[0] to v }.groupBy { it.first }.mapValues { (_, v) -> v.sumOf { it.second } }
        return map2.maxOf { it.value } - map2.minOf { it.value }
    }
}
