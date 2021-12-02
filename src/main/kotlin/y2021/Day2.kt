package y2021

class Day2(input: String) {
    private val instructions: List<Pair<Direction, Int>> = input.lines().filter { it.isNotBlank() }.map { l ->
        val (direction, value) = l.split(" ")
        when (direction) {
            "up" -> Direction.UP
            "forward" -> Direction.FORWARD
            "down" -> Direction.DOWN
            else -> throw IllegalArgumentException()
        } to value.toInt()
    }

    enum class Direction {
        FORWARD,
        UP,
        DOWN,
    }

    private fun part1(): Int {
        var depth = 0
        var position = 0
        instructions.forEach { (direction, value) ->
            when (direction) {
                Direction.FORWARD -> position += value
                Direction.UP -> depth -= value
                Direction.DOWN -> depth += value
            }
        }
        return depth * position
    }

    private fun part2(): Int {
        var aim = 0
        var depth = 0
        var position = 0
        instructions.forEach { (direction, value) ->
            when (direction) {
                Direction.FORWARD -> {
                    position += value
                    depth += aim * value
                }
                Direction.UP -> aim -= value
                Direction.DOWN -> aim += value
            }
        }
        return depth * position
    }

    override fun toString(): String {
        return "Part1: ${part1()}, Part2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2021/day2/$fileName.txt")

fun main() {
    println(Day2(readInput("test")))
    println(Day2(readInput("input")))
}
