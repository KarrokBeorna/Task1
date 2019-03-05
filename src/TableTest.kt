import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException

internal class TableTest {

    private val pair1 = 28.06 to 19.72
    private val pair2 = 2.12 to 197.3
    private val pair3 = 15.06 to 20.00
    private val pair4 = 9.12 to 199.5

    @Test
    fun adding() {
        val family = Table(sortedMapOf(pair1, pair2, pair4))
        family.adding(pair3)
        assertEquals(Table(sortedMapOf(pair1, pair2, pair3, pair4)), family)

        val trial = Table(sortedMapOf(pair1, pair2, pair3, pair4))
        trial.adding(pair4)
        assertEquals(Table(sortedMapOf(pair1, pair2, pair3, pair4)), trial)
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
        assertEquals(pair3, Table(sortedMapOf(pair2, pair3, pair4)).search(15.06))
        assertEquals(pair3, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(21.56))
        assertEquals(pair4, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(5.63))
        assertEquals(pair3, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(15.06))
        assertEquals(pair1, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(40.05))
        assertEquals(pair2, Table(sortedMapOf(pair1, pair2, pair3, pair4)).search(0.05))
    }

    @Test
    fun interpol() {
        assertEquals(21.56 to 19.86, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(21.56))
        assertEquals(5.62 to 198.4, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(5.62))
        assertEquals(15.06 to 20.00, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(15.06))
        assertEquals(10.0 to 172.91, Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(10.0))
        assertThrows(NoSuchElementException::class.java) {Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(222.22)}
        assertThrows(NoSuchElementException::class.java) {Table(sortedMapOf(pair1, pair2, pair3, pair4)).interpol(1.01)}
    }

    @Test
    fun issue() {
        assertEquals(mapOf(pair1, pair2, pair3, pair4), Table(sortedMapOf(pair1, pair2, pair3, pair4)).issue())
    }
}