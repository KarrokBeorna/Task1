import javax.swing.JOptionPane

/**fun adding(mapa: MutableMap<Int, Int>): MutableMap<Int, Int> {
    val x = Integer.valueOf(JOptionPane.showInputDialog("Введите аргумент"))
    val y = Integer.valueOf(JOptionPane.showInputDialog("Введите значение"))
    mapa[x] = y
    println(mapa)
    return mapa
}

fun removing(mapa: MutableMap<Int, Int>): Map<Int, Int> {
    val x = Integer.valueOf(JOptionPane.showInputDialog("Введите аргумент"))
    mapa.remove(x)
    println(mapa)
    return mapa
}

fun issue(ans: MutableSet<Pair<Int, Int>>): Set<Pair<Int, Int>> {
    val k = Integer.valueOf(JOptionPane.showInputDialog("Введите кол-во пар"))
    for (i in 1..k) {
        val x = Integer.valueOf(JOptionPane.showInputDialog("Введите аргумент"))
        val y = Integer.valueOf(JOptionPane.showInputDialog("Введите значение"))
        ans.add(x to y)
        println(ans)
    }
    return ans
}

fun search(mapa: Map<Int, Int>): Pair<Int, Int> {
    val x = Integer.valueOf(JOptionPane.showInputDialog("Введите аргумент"))
    val sortedMapa = mapa.toSortedMap()
    val z = sortedMapa.firstKey()
    val l = sortedMapa.lastKey()
    var new = Pair(z, sortedMapa[z]!!)
    when {
        z >= x -> {
            println(new); return new
        }
        l <= x -> new = Pair(l, sortedMapa[l]!!)
        else -> for ((key, value) in sortedMapa) {
            new = if (new.first < key && key < x) key to value
            else {
                if (x - new.first <= key - x) {
                    println(new); return new
                } else key to value
            }
        }
    }
    println(new)
    return new
}

fun interpol(mapa: Map<Int, Int>): Int {
    val x = Integer.valueOf(JOptionPane.showInputDialog("Введите аргумент"))
    var y = 0
    val sortedMapa = mapa.toSortedMap()
    var z = sortedMapa.firstKey()
    var l = sortedMapa.lastKey()
    var fP = Pair(z, sortedMapa[z]!!)
    val sP: Pair<Int, Int>
    when {
        z > x -> {
            sortedMapa.remove(z); l = sortedMapa.firstKey()
            y = mapa[z]!! + (mapa[l]!! - mapa[z]!!) * (x - z) / (l - z)
        }
        l < x -> {
            sortedMapa.remove(l); z = sortedMapa.lastKey()
            y = mapa[z]!! + (mapa[l]!! - mapa[z]!!) * (x - z) / (l - z)
        }
        else -> for ((key, value) in sortedMapa) {
            when {
                key == x -> {
                    println(value); return value
                }
                fP.first <= key && key < x -> fP = key to value
                else -> {
                    sP = key to value
                    y = fP.second + (sP.second - fP.second) * (x - fP.first) / (sP.first - fP.first)
                    println(y)
                    return y
                }
            }
        }
    }
    println(y)
    return y
} */



data class Table(var excel: MutableMap<Double, Double>) {

    fun adding(pair: Pair<Double, Double>): MutableMap<Double, Double> {
        if (pair.first !in excel.keys)
            excel[pair.first] = pair.second
        return excel
    }

    fun removing(x: Double): MutableMap<Double, Double> {
        excel.remove(x)
        return excel
    }

    fun search(x: Double): Pair<Double, Double> {
        val sortedMap = excel.toSortedMap()
        val first = sortedMap.firstKey()
        val last = sortedMap.lastKey()
        var firstPair = Pair(first, sortedMap[first]!!)
        when {
            first >= x -> return firstPair
            last <= x -> return Pair(last, sortedMap[last]!!)
            else -> for ((key, value) in sortedMap) {
                when {
                    firstPair.first <= key && key < x -> firstPair = key to value
                    else -> return if (x - firstPair.first <= key - x) firstPair
                    else key to value
                }
            }
        }
        return firstPair
    }

    fun interpol(x: Double): Pair<Double, Double> {
        val sortedMap = excel.toSortedMap()
        var y = 0.0
        var first = sortedMap.firstKey()
        var last = sortedMap.lastKey()
        var fP = Pair(first, sortedMap[first]!!)
        val sP: Pair<Double, Double>
        when {
            first > x -> {
                sortedMap.remove(first); last = sortedMap.firstKey()
                y = excel[first]!! + (excel[last]!! - excel[first]!!) * (x - first) / (last - first)
            }
            last < x -> {
                sortedMap.remove(last); first = sortedMap.lastKey()
                y = excel[first]!! + (excel[last]!! - excel[first]!!) * (x - first) / (last - first)
            }
            else -> for ((key, value) in sortedMap) {
                when {
                    key == x -> return key to value
                    fP.first <= key && key < x -> fP = key to value
                    else -> {
                        sP = key to value
                        y = fP.second + (sP.second - fP.second) * (x - fP.first) / (sP.first - fP.first)
                        y = Math.round(y * 100.0) / 100.0
                        return x to y
                    }
                }
            }
        }
        y = Math.round(y * 100.0) / 100.0
        return x to y
    }

    fun issue(): MutableSet<Pair<Double, Double>> {
        val answer = mutableSetOf<Pair<Double, Double>>()
        for ((x, y) in excel.toSortedMap()) {
            answer.add(x to y)
            println(x to y)
        }
        return answer
    }
}
