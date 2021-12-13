package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day10Test {

    private val dayTwelve = Day10()

    @Test
    fun testPartOne() {
        assertThat(dayTwelve.partOne(), `is`(26397))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwelve.partTwo(), `is`(288957))
    }
}
