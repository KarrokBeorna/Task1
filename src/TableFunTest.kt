import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestFactory
import javax.swing.JOptionPane

internal class TableFunTest {


    @Test
    fun adding() {
        assertNotEquals(mapOf<Int, Int>(), adding(mutableMapOf(3 to 3, 4 to 2, 5 to 1)))
        assertNotEquals(mapOf<Int, Int>(), adding(mutableMapOf(5 to 72, 8 to 838, 292398 to 2388)))
        assertNotEquals(mapOf<Int, Int>(), adding(mutableMapOf(12 to 22, 1 to 3, 6 to 9)))
    }

    @Test
    fun removing() {
        assertEquals(mapOf(3 to 3, 4 to 2), removing(mutableMapOf(3 to 3, 4 to 2, 5 to 1)))
        assertEquals(mapOf(4 to 2, 5 to 1), removing(mutableMapOf(3 to 3, 4 to 2, 5 to 1)))
        assertEquals(mapOf(3 to 3, 5 to 1), removing(mutableMapOf(3 to 3, 4 to 2, 5 to 1)))
    }

    @Test
    fun issue() {
        assertNotEquals(setOf<Pair<Int, Int>>(), issue(mutableSetOf()))
    }

    @Test
    fun search() {
        assertNotEquals(Pair(Int, Int), search(mapOf(123 to 11, 22 to 6, 44 to 12)))
        assertNotEquals(Pair(Int, Int), search(mapOf(123 to 11, 22 to 6, 44 to 12)))
        assertNotEquals(Pair(Int, Int), search(mapOf(123 to 11, 22 to 6, 44 to 12)))
        assertNotEquals(Pair(Int, Int), search(mapOf(123 to 11, 22 to 6, 44 to 12)))
    }

    @Test
    fun interpol() {
        assertNotEquals(Pair(Int, Int),interpol(mapOf(122 to 36, 22 to 6, 44 to 12)))
        assertNotEquals(Pair(Int, Int),interpol(mapOf(122 to 36, 22 to 6, 44 to 12)))
        assertNotEquals(Pair(Int, Int),interpol(mapOf(122 to 36, 22 to 6, 44 to 12)))
        assertNotEquals(Pair(Int, Int),interpol(mapOf(122 to 36, 22 to 6, 44 to 12)))
    }
}