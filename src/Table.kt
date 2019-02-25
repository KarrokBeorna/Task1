import javax.swing.JOptionPane

fun adding(mapa: MutableMap<Int, Int>): MutableMap<Int, Int> {
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
        z > x -> { sortedMapa.remove(z); l = sortedMapa.firstKey()
            y = mapa[z]!! + (mapa[l]!! - mapa[z]!!) * (x - z) / (l - z) }
        l < x -> { sortedMapa.remove(l); z = sortedMapa.lastKey()
            y = mapa[z]!! + (mapa[l]!! - mapa[z]!!) * (x - z) / (l - z) }
        else -> for ((key, value) in sortedMapa) {
            when {
                key == x -> { println(value); return value }
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
}
