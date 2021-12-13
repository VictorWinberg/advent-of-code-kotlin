package days

import java.util.*

class Day10 : Day(10) {

    override fun partOne(): Any {
        var incorrect = 0

        inputList.forEach { line ->
            val stack = ArrayDeque<Char>()
            line.toList().forEach next@{ char ->
                if ("([{<".contains(char)) {
                    stack.push(char)
                } else if (")]}>".contains(char)) {
                    val openChar = stack.pop()
                    if (opening(char) != openChar) {
                        incorrect += when (char) {
                            ')' -> 3
                            ']' -> 57
                            '}' -> 1197
                            '>' -> 25137
                            else -> throw Error("Missing $char")
                        }
                        return@next
                    }
                } else throw Error("Missing char $char")
            }
        }
        return incorrect
    }

    override fun partTwo(): Any {
        val list = inputTestList.filter { line ->
            val stack = ArrayDeque<Char>()
            line.toList().forEach { char ->
                if ("([{<".contains(char)) {
                    stack.push(char)
                } else if (")]}>".contains(char)) {
                    val openChar = stack.pop()
                    if (opening(char) != openChar) {
                        return@filter false
                    }
                } else throw Error("Missing char $char")
            }
            true
        }
        return list
    }

    private fun opening(char: Char) = when (char) {
        ')' -> '('
        ']' -> '['
        '}' -> '{'
        '>' -> '<'
        else -> throw Error("Missing closing for $char")
    }
}
