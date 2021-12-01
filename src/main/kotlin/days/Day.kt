package days

import util.InputReader

abstract class Day(dayNumber: Int) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }
    protected val inputTestList: List<String> by lazy { InputReader.getTestInputAsList(dayNumber) }
    protected val inputTestString: String by lazy { InputReader.getTestInputAsString(dayNumber) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
