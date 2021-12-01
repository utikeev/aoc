package y2015

class Day3(private val input: String) {

    private class Deliver(private var x: Int = 0, private var y: Int = 0) {
        val position: Pair<Int, Int>
            get() = x to y

        fun move(char: Char) {
            when (char) {
                '>' -> x++
                '<' -> x--
                '^' -> y++
                'v' -> y--
            }
        }
    }

    private fun part1(): Int {
        val santa = Deliver()
        val visitedHouses = mutableSetOf(santa.position)
        input.forEach {
            santa.move(it)
            visitedHouses.add(santa.position)
        }
        return visitedHouses.size
    }

    private fun part2(): Int {
        val santa = Deliver()
        val roboSanta = Deliver()
        val visitedHouses = mutableSetOf(santa.position)
        input.forEachIndexed { i, c ->
            val currentDeliver = if (i % 2 == 0) santa else roboSanta
            currentDeliver.move(c)
            visitedHouses.add(currentDeliver.position)
        }
        return visitedHouses.size
    }

    override fun toString(): String {
        return "Part 1: ${part1()}, Part 2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2015/day3/$fileName.txt")

fun main() {
    println(Day3(readInput("test1")))
    println(Day3(readInput("test2")))
    println(Day3(readInput("test3")))
    println(Day3(readInput("test4")))
    println(Day3(readInput("input")))
}
