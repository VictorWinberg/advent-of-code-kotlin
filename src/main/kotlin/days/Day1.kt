package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputList.map { it.toInt() }.zipWithNext().filter { it.first < it.second }.size
    }

    override fun partTwo(): Any {
        val list = inputList.map { it.toInt() }
        val n = 2
        return list.dropLast(n).indices
            .map { index -> (0..n).sumOf { offset -> list[index + offset] } }
            .zipWithNext().filter { it.first < it.second }.size
    }
}
