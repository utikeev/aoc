package y2015

import Resources
import rotateLeft

class Day2(input: String) {
    private val sides = input.lines().filter { it.isNotBlank() }.map { line ->
        line.split("x").map { it.toInt() }
    }

    private fun part1(): Int = sides.sumOf { side ->
        val areas = side.zip(side.rotateLeft(1)) { a, b -> a * b }
        2 * areas.sum() + areas.minOf { it }
    }

    private fun part2(): Int = sides.sumOf { side ->
        val (a, b) = side.sorted().take(2)
        2 * (a + b) + side.reduce(Int::times)
    }

    override fun toString(): String {
        return "Part 1: ${part1()}, Part 2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2015/day2/$fileName.txt")

fun main() {
    val testInput = readInput("test")
    val input = readInput("input")
    println(Day2(testInput))
    println(Day2(input))
}
