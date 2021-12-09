package days

class Day8 : Day(8) {

    override fun partOne(): Any {
        val input = inputList.map { it.split(" | ")[1].split(" ") }
        return input.sumOf { it.count { it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7 } }
    }

    override fun partTwo(): Any {
        return inputList.sumOf { line -> decrypt(line) }
    }

    private fun decrypt(string: String): Int {
        val input = string.split(" | ")[0].split(" ")
        val output = string.split(" | ")[1].split(" ")

        val mutation = allPermutations("abcdefg".toSet()).find { chars ->
            input.all { segment -> sevenSegment(segment.map { chars[it - 'a'] }.toString()) != null }
        } ?: throw Error("Couldn't find mutation!")

        return output
            .map { segment ->
                sevenSegment(segment.map { mutation[it - 'a'] }.toString()) ?: throw Error("Incorrect mutation!")
            }
            .joinToString("").toInt()
    }

    private fun sevenSegment(input: String): Int? {
        val a = input.contains('a')
        val b = input.contains('b')
        val c = input.contains('c')
        val d = input.contains('d')
        val e = input.contains('e')
        val f = input.contains('f')
        val g = input.contains('g')

        if (listOf(a, b, c, e, f, g).all { it } && listOf(d).none { it }) return 0
        if (listOf(c, f).all { it } && listOf(a, b, d, e, g).none { it }) return 1
        if (listOf(a, c, d, e, g).all { it } && listOf(b, f).none { it }) return 2
        if (listOf(a, c, d, f, g).all { it } && listOf(b, e).none { it }) return 3
        if (listOf(b, c, d, f).all { it } && listOf(a, e, g).none { it }) return 4
        if (listOf(a, b, d, f, g).all { it } && listOf(c, e).none { it }) return 5
        if (listOf(a, b, d, e, f, g).all { it } && listOf(c).none { it }) return 6
        if (listOf(a, c, f).all { it } && listOf(b, d, e, g).none { it }) return 7
        if (listOf(a, b, c, d, e, f, g).all { it }) return 8
        if (listOf(a, b, c, d, f, g).all { it } && listOf(e).none { it }) return 9

        return null
    }

    private fun <T> allPermutations(set: Set<T>): Set<List<T>> {
        if (set.isEmpty()) return emptySet()

        fun <T> recAllPermutations(list: List<T>): Set<List<T>> {
            if (list.isEmpty()) return setOf(emptyList())

            val result: MutableSet<List<T>> = mutableSetOf()
            for (i in list.indices) {
                recAllPermutations(list - list[i]).forEach { item ->
                    result.add(item + list[i])
                }
            }
            return result
        }

        return recAllPermutations(set.toList())
    }
}
