package days

class Day3 : Day(3) {

    override fun partOne(): Any {
        val list = inputList[0].toList().map { mutableListOf(0, 0) }
        inputList.forEach {
            it.toList().map { i -> i.toString().toInt() }.forEachIndexed { index, i -> list[index][i] += 1 }
        }
        val gammaRate = list.map { pair -> if (pair[0] < pair[1]) 1 else 0 }.joinToString("").toInt(2)
        val epsilonRate = list.map { pair -> if (pair[0] < pair[1]) 0 else 1 }.joinToString("").toInt(2)
        return gammaRate * epsilonRate
    }

    override fun partTwo(): Any {
        val oxygen = rating(inputList.toMutableList(), inputList[0].length) { a, b -> if (a <= b) '1' else '0' }
        val co2 = rating(inputList.toMutableList(), inputList[0].length) { a, b -> if (a <= b) '0' else '1' }
        return co2 * oxygen
    }

    private fun rating(list: MutableList<String>, bitsLength: Int, op: (Int, Int) -> Char) =
        (0 until bitsLength).map { index ->
            val bits = mutableListOf(0, 0)
            if (list.size == 1) return@map list[0][index]
            list.forEach { bits[it.toList()[index].toString().toInt()] += 1 }
            list.removeAll { it[index] != op(bits[0], bits[1]) }
            op(bits[0], bits[1])
        }.joinToString("").toInt(2)
}
