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
            input.all { segment -> sevenSegment(segment.map { chars[it - 'a'] }) != null }
        } ?: throw Error("Couldn't find mutation!")

        return output
            .map { segment ->
                sevenSegment(segment.map { mutation[it - 'a'] }) ?: throw Error("Incorrect mutation!")
            }
            .joinToString("").toInt()
    }

    private fun sevenSegment(input: List<Char>): Int? {
        val sortedIn = input.sorted().joinToString("")

        if (sortedIn == "abcefg") return 0
        if (sortedIn == "cf") return 1
        if (sortedIn == "acdeg") return 2
        if (sortedIn == "acdfg") return 3
        if (sortedIn == "bcdf") return 4
        if (sortedIn == "abdfg") return 5
        if (sortedIn == "abdefg") return 6
        if (sortedIn == "acf") return 7
        if (sortedIn == "abcdefg") return 8
        if (sortedIn == "abcdfg") return 9

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
