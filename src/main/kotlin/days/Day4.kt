package days

class Day4 : Day(4) {

    override fun partOne(): Any {
        return winners().first()
    }

    override fun partTwo(): Any {
        return winners().last()
    }

    private fun winners(): List<Int> {
        val list = inputList.filter { it.isNotEmpty() }
        val numbers = list.first().split(",").map { it.toInt() }
        val size = 5

        val boards = list.drop(1).map { it.trim().split(Regex("\\s+")).map { it.toInt() } }.chunked(size)
        val marked = Array(boards.size) { Array(size) { BooleanArray(size) { false } } }
        return winners(numbers, boards, marked)
    }

    private fun winners(
        numbers: List<Int>,
        boards: List<List<List<Int>>>,
        booleans: Array<Array<BooleanArray>>
    ): List<Int> {
        val winnerBoards = HashMap<Int, Pair<Int, Int>>()
        numbers.forEachIndexed { round, number ->
            boards.forEachIndexed { index, board ->
                val marked = booleans[index]
                board.forEachIndexed { y, row ->
                    val x = row.indexOf(number)
                    if (x > -1) marked[y][x] = true
                }

                if (hasBingo(marked)) {
                    val unmarked = board.flatten().zip(marked.map { it.toList() }.flatten()).filter { !it.second }
                    winnerBoards.putIfAbsent(index, Pair(round, unmarked.sumOf { it.first } * number))
                }
            }
        }
        return winnerBoards.values.sortedBy { it.first }.map { it.second }
    }

    private fun hasBingo(booleans: Array<BooleanArray>): Boolean {
        booleans.forEach { row -> if (row.all { it }) return true }
        transpose(booleans).forEach { col -> if (col.all { it }) return true }
        return false
    }

    private fun transpose(A: Array<BooleanArray>): List<List<Boolean>> {
        return A.indices.map { i -> A.indices.map { j -> A[j][i] } }
    }
}
