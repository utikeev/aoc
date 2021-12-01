package y2021

class Day1(private val input: List<Int>) {
    private fun part1(): Int = input.countIncreases()

    private fun part2(): Int = input.windowed(3).map { it.sum() }.countIncreases()

    private fun List<Int>.countIncreases(): Int = zipWithNext { a, b -> a < b }.count { it }

    override fun toString(): String {
        return "Part 1: ${part1()}, Part 2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsListOfInt("y2021/day1/$fileName.txt")

fun main() {
    val testInput = readInput("test")
    val input = readInput("input")
    println(Day1(testInput))
    println(Day1(input))
}
