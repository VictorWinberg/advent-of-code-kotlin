package days

class Day2 : Day(2) {

    override fun partOne(): Any {
        var horizontal = 0
        var depth = 0
        inputList.map { it.split(" ") }.forEach {
            val dir = it[0]
            val amount = it[1].toInt()
            when (dir) {
                "forward" -> horizontal += amount
                "down" -> depth += amount
                "up" -> depth -= amount
                else -> throw Error("missing direction $dir")
            }
        }
        return horizontal * depth
    }

    override fun partTwo(): Any {
        var horizontal = 0
        var depth = 0
        var aim = 0
        inputList.map { it.split(" ") }.forEach {
            val dir = it[0]
            val amount = it[1].toInt()
            when (dir) {
                "forward" -> {
                    horizontal += amount
                    depth += aim * amount
                }
                "down" -> aim += amount
                "up" -> aim -= amount
                else -> throw Error("missing direction $dir")
            }
        }
        return horizontal * depth
    }
}
