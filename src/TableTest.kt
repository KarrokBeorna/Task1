import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TableTest {

    private val pair1 = 28.06 to 19.72
    private val pair2 = 2.12 to 197.3
    private val pair3 = 15.06 to 20.00
    private val pair4 = 9.12 to 199.5

    @Test
    fun adding() {
        assertTrue(Table(sortedMapOf(pair1, pair2, pair3)).adding(15.07 to 20.00))
        assertFalse(Table(sortedMapOf(pair1, pair2, pair3, pair4)).adding(15.06 to 20.01))
    }

    @Test
    fun removing() {
        val test1 = Table(sortedMapOf(pair1, pair2, pair3, pair4))
        test1.removing(15.06)
        assertEquals(Table(sortedMapOf(pair1, pair2, pair4)), test1)

        val test2 = Table(sortedMapOf(pair1, pair2, pair3, pair4))
        test2.removing(15.07)
        assertEquals(Table(sortedMapOf(pair1, pair2, pair3, pair4)), test2)
    }

    @Test
    fun search() {
        assertEquals(pair3, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(13.05))
        assertEquals(pair3, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search((18.09)))
        assertEquals(pair3, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(21.56))
        assertEquals(pair4, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(5.63))
    }

    @Test
    fun interpol() {
        assertEquals(21.56 to 19.86, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(21.56))
        assertEquals(5.62 to 198.4, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(5.62))
        assertEquals(15.06 to 20.00, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(15.06))
        assertEquals(10.0 to 172.91, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(10.0))
    }

    @Test
    fun issue() {
        assertEquals(mapOf(pair1, pair2, pair3, pair4), Table(sortedMapOf(pair1, pair2, pair3, pair4)).issue())
    }
}