package y2015

class Day5(input: String) {
    private val strings = input.lines().filter { it.isNotBlank() }

    private fun Char.isVowel(): Boolean = when (this) {
        'a', 'e', 'i', 'o', 'u' -> true
        else -> false
    }

    private fun part1(): Int = strings.count { s ->
        var vowelsCount = 0
        var hasDuplicated = false
        s.zipWithNext().forEach { (a, b) ->
            if (a.isVowel()) vowelsCount++
            if (a == b) hasDuplicated = true
            when ("$a$b") {
                "ab", "cd", "pq", "xy" -> return@count false
            }
        }
        if (s.last().isVowel()) vowelsCount++
        hasDuplicated && vowelsCount >= 3
    }

    private fun part2(): Int = strings.count { s ->
        var hasGoodPair = false
        var hasPalindrome = false
        val pairs = mutableMapOf<String, Int>()
        val length = s.length
        s.indices.forEach { i ->
            if (i < length - 1) {
                val pair = "${s[i]}${s[i + 1]}"
                if (pair !in pairs) {
                    pairs[pair] = i
                } else if (pairs[pair] != i - 1) {
                    hasGoodPair = true
                }
            }
            if (i < length - 2) {
                if (s[i] == s[i + 2]) {
                    hasPalindrome = true
                }
            }
        }
        hasGoodPair && hasPalindrome
    }

    override fun toString(): String {
        return "Part1: ${part1()}, Part2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2015/day5/$fileName.txt")

fun main() {
    println(Day5(readInput("test")))
    println(Day5(readInput("test2")))
    println(Day5(readInput("input")))
}
