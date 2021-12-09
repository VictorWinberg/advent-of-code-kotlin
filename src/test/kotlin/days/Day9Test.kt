package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day9Test {

    private val dayNine = Day9()

    @Test
    fun testPartOne() {
        assertThat(dayNine.partOne(), `is`(15))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayNine.partTwo(), `is`(1134))
    }
}
