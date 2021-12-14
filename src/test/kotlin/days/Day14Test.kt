package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day14Test {

    private val dayFourteen = Day14()

    @Test
    fun testPartOne() {
        assertThat(dayFourteen.partOne(), `is`(1588))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFourteen.partTwo(), `is`(2188189693529))
    }
}
