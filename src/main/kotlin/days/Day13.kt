package days

class Day13 : Day(13) {

    private fun parseInput(): Pair<List<Pair<Int, Int>>, List<Pair<String, Int>>> {
        var input =
            inputString.split("\n\n")[0].split("\n").map { Pair(it.split(",")[0].toInt(), it.split(",")[1].toInt()) }
        val instructions =
            inputString.split("\n\n")[1].split("\n").map { Pair(it.split("=")[0], it.split("=")[1].toInt()) }
        return Pair(input, instructions)
    }

    override fun partOne(): Any {
        val (input, instructions) = parseInput()
        val output = foldPaper(input, instructions[0])

        return output.size
    }

    override fun partTwo(): Any {
        val (input, instructions) = parseInput()
        var output: List<Pair<Int, Int>> = input

        instructions.forEach { instruction -> output = foldPaper(output, instruction) }

        var size = Pair(output.maxOfOrNull { it.first }!!, output.maxOfOrNull { it.second }!!)

        return "\n" + (0..size.second).joinToString("\n") { y ->
            (0..size.first).joinToString("") { x ->
                if (output.contains(Pair(x, y))) "#"
                else "."
            }
        }
    }

    private fun foldPaper(
        input: List<Pair<Int, Int>>,
        instruction: Pair<String, Int>
    ) = input.fold<Pair<Int, Int>, List<Pair<Int, Int>>>(listOf()) { acc, (x, y) ->
        when (instruction.first) {
            "fold along x" -> if (instruction.second == x) acc else acc + Pair(
                if (x > instruction.second) 2 * instruction.second - x else x,
                y
            )
            "fold along y" -> if (instruction.second == y) acc else acc + Pair(
                x,
                if (y > instruction.second) 2 * instruction.second - y else y
            )
            else -> throw Error("Missing instruction")
        }
    }.toSet().toList()
}
