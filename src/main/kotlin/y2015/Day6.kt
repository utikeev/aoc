package y2015

class Day6(input: String) {
    private enum class InstructionType {
        TURN_ON,
        TURN_OFF,
        TOGGLE
    }

    private data class Instruction(val type: InstructionType, val p1: Pair<Int, Int>, val p2: Pair<Int, Int>)

    private val instructionRegex = Regex("""(.+)\s(\d+),(\d+) through (\d+),(\d+)""")

    private val instructions = input.lines().filter { it.isNotBlank() }.mapNotNull { l ->
        instructionRegex.matchEntire(l)?.let { match ->
            val type = when (match.groupValues[1]) {
                "turn on" -> InstructionType.TURN_ON
                "turn off" -> InstructionType.TURN_OFF
                "toggle" -> InstructionType.TOGGLE
                else -> throw IllegalArgumentException()
            }
            val p1 = match.groupValues[2].toInt() to match.groupValues[3].toInt()
            val p2 = match.groupValues[4].toInt() to match.groupValues[5].toInt()
            Instruction(type, p1, p2)
        }
    }

    private fun List<MutableList<Int>>.applyInRange(p1: Pair<Int, Int>, p2: Pair<Int, Int>, fn: (Int) -> Int) {
        val (x1, y1) = p1
        val (x2, y2) = p2
        (x1..x2).forEach { x ->
            (y1..y2).forEach { y ->
                this[x][y] = fn(this[x][y])
            }
        }
    }

    private fun part1(): Int {
        val grid = List(1000) { MutableList(1000) { 0 } }
        instructions.forEach { i ->
            val fn: (Int) -> Int = when (i.type) {
                InstructionType.TURN_ON -> { _ -> 1 }
                InstructionType.TURN_OFF -> { _ -> 0 }
                InstructionType.TOGGLE -> { it -> if (it == 1) 0 else 1 }
            }
            grid.applyInRange(i.p1, i.p2, fn)
        }
        return grid.sumOf { row -> row.count { it == 1 } }
    }

    private fun part2(): Int {
        val grid = List(1000) { MutableList(1000) { 0 } }
        instructions.forEach { i ->
            val fn: (Int) -> Int = when (i.type) {
                InstructionType.TURN_ON -> { it -> it + 1 }
                InstructionType.TURN_OFF -> { it -> maxOf(it - 1, 0) }
                InstructionType.TOGGLE -> { it -> it + 2 }
            }
            grid.applyInRange(i.p1, i.p2, fn)
        }
        return grid.sumOf { row -> row.sum() }
    }

    override fun toString(): String {
        return "Part1: ${part1()}, Part2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2015/day6/$fileName.txt")

fun main() {
    println(Day6(readInput("test1")))
    println(Day6(readInput("test2")))
    println(Day6(readInput("test3")))
    println(Day6(readInput("input")))
}
