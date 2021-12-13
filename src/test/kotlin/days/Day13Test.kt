package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day13Test {

    private val dayThirteen = Day13()

    @Test
    fun testPartOne() {
        assertThat(dayThirteen.partOne(), `is`(17))
    }

    @Test
    fun testPartTwo() {
        assertThat(
            dayThirteen.partTwo(), `is`(
                "\n" +
                        "#####" + "\n" +
                        "#...#" + "\n" +
                        "#...#" + "\n" +
                        "#...#" + "\n" +
                        "#####"
            )
        )
    }
}
