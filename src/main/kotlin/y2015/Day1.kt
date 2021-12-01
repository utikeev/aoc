package y2015

class Day1(private val input: String) {

    private fun part1(): Int = input.fold(0) { acc, c -> if (c == '(') acc + 1 else acc - 1 }

    private fun part2(): Int = input.runningFold(0) { acc, c -> if (c == '(') acc + 1 else acc - 1 }.indexOfFirst { it < 0 }

    override fun toString(): String {
        return "Part1: ${part1()}, Part2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2015/day1/$fileName.txt")

fun main() {
    val input = readInput("input").trim()
    println(Day1("(())"))
    println(Day1("()()"))
    println(Day1("((("))
    println(Day1("(()(()("))
    println(Day1("))((((("))
    println(Day1("())"))
    println(Day1("))("))
    println(Day1(")))"))
    println(Day1(")())())"))
    println(Day1(")"))
    println(Day1("()())"))
    println(Day1(input))
}
