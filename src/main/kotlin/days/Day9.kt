package days

class Day9 : Day(9) {

    private fun parseInput() = inputList.map { it.toList().map { it.toString().toInt() } }

    override fun partOne(): Any {
        val input = parseInput()
        return locations(input).sumOf { (x, y) -> input[y][x] + 1 }
    }

    override fun partTwo(): Any {
        val input = parseInput()
        return locations(input)
            .map { (x, y) -> rec(input, x, y, HashMap()) }
            .sortedDescending()
            .take(3)
            .fold(1) { acc, i -> acc * i }
    }

    private fun rec(input: List<List<Int>>, x: Int, y: Int, visited: HashMap<Pair<Int, Int>, Boolean>): Int {
        if (visited.putIfAbsent(Pair(y, x), true) == true) return 0

        val basins = listOf(Pair(y - 1, x), Pair(y + 1, x), Pair(y, x + 1), Pair(y, x - 1))
            .map { pos -> Pair(pos, input.getOrNull(pos.first)?.getOrNull(pos.second) ?: 9) }
            .filter { it.second != 9 }

        return 1 + basins.sumOf { rec(input, it.first.second, it.first.first, visited) }
    }

    private fun locations(input: List<List<Int>>): MutableList<Pair<Int, Int>> {
        val locations = mutableListOf<Pair<Int, Int>>()
        input.indices.forEach { y ->
            input[0].indices.forEach { x ->
                val lowestNeighbour = listOf(Pair(y - 1, x), Pair(y + 1, x), Pair(y, x + 1), Pair(y, x - 1))
                    .map { pos -> input.getOrNull(pos.first)?.getOrNull(pos.second) ?: 9 }
                    .minOf { it }

                if (input[y][x] < lowestNeighbour) {
                    locations.add(Pair(x, y))
                }
            }
        }
        return locations
    }
}
