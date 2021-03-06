import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*

data class Table(private val excel: SortedMap<Double, Double>) {

    fun adding(pair: Pair<Double, Double>) {
        if (excel[pair.first] == null)
            excel[pair.first] = pair.second
        excel
    }

    fun removing(x: Double) = excel.remove(x)

    fun search(x: Double): Pair<Double, Double> {
        return when {
            x <= excel.firstKey() -> excel.firstKey() to excel[excel.firstKey()]!!
            x >= excel.lastKey() -> excel.lastKey() to excel[excel.lastKey()]!!
            else -> {
                val first = excel.headMap(x).lastKey()
                val second = excel.tailMap(x).firstKey()
                when {
                    x - first <= second - x -> first to excel[first]!!
                    else -> second to excel[second]!!
                }
            }
        }
    }

    fun interpol(x: Double): Pair<Double, Double> {
        return when {
            x <= excel.firstKey() || x >= excel.lastKey() -> throw IllegalArgumentException()
            else -> {
                val first = excel.headMap(x).lastKey()
                val second = excel.tailMap(x).firstKey()
                val y = excel[first]!! + (excel[second]!! - excel[first]!!) * (x - first) / (second - first)
                when (x) {
                    first, second -> x to excel[x]!!
                    else -> x to Math.round(y * 100.0) / 100.0
                }
            }
        }
    }


    fun issue(): Map<Double, Double> {
        val copyExcel = mutableMapOf<Double, Double>()
        excel.forEach { copyExcel[it.key] = it.value }
        return copyExcel
    }
}

