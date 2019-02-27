import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TableTest {

    val pair1 = 28.06 to 19.72
    val pair2 = 2.12 to 197.3
    val pair3 = 15.06 to 20.00
    val pair4 = 9.12 to 199.5

    @Test
    fun adding() {
        val family = Table(mutableMapOf(pair1, pair2, pair4))
        family.adding(pair3)
        assertEquals(Table(mutableMapOf(pair1, pair2, pair3, pair4)), family)

        val trial = Table(mutableMapOf(pair1, pair2, pair3, pair4))
        trial.adding(15.06 to 200.1)
        assertEquals(Table(mutableMapOf(pair1, pair2, pair3, pair4)), trial)
    }

    @Test
    fun removing() {
        val test1 = Table(mutableMapOf(pair1, pair2, pair3, pair4))
        test1.removing(15.06)
        assertEquals(Table(mutableMapOf(pair1, pair2, pair4)), test1)

        val test2 = Table(mutableMapOf(pair1, pair2, pair3, pair4))
        test2.removing(15.07)
        assertEquals(Table(mutableMapOf(pair1, pair2, pair3, pair4)), test2)
    }

    @Test
    fun search() {
        assertEquals(pair3, Table(mutableMapOf(pair1, pair3)).search(13.05))
        assertEquals(pair3, Table(mutableMapOf(pair1, pair2, pair3)).search((18.09)))
        assertEquals(pair3, Table(mutableMapOf(pair1, pair3)).search(21.56))
        assertEquals(pair4, Table(mutableMapOf(pair1, pair2, pair3, pair4)).search(5.63))
    }

    @Test
    fun interpol() {
        assertEquals(21.56 to 19.86, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(21.56))
        assertEquals(41.06 to 19.44, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(41.06))
        assertEquals(5.62 to 198.4, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(5.62))
        assertEquals(-4.88 to 195.1, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(-4.88))
        assertEquals(15.06 to 20.00, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(15.06))
        assertEquals(0.0 to 196.63, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(0.0))
        assertEquals(10.0 to 172.91, Table(mutableMapOf(pair1, pair2, pair3, pair4)).interpol(10.0))
    }

    @Test
    fun issue() {
        assertEquals(mutableSetOf(pair2, pair4, pair3, pair1), Table(mutableMapOf(pair1, pair2, pair3, pair4)).issue())
    }
}