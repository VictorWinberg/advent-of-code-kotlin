package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputList.map { it.toInt() }.zipWithNext().count { it.first < it.second }
    }

    override fun partTwo(): Any {
        val list = inputList.map { it.toInt() }
        return list.dropLast(2).indices
            .map { index -> (0..2).sumOf { offset -> list[index + offset] } }
            .zipWithNext().count { it.first < it.second }
    }
}
