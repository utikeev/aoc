package y2015

import Resources
import java.math.BigInteger
import java.security.MessageDigest

class Day4(private val input: String) {

    private fun md5Hash(password: Long): String {
        val md5 = MessageDigest.getInstance("MD5")
        md5.update((input + password).toByteArray())
        return String.format("%032x", BigInteger(1, md5.digest()))
    }

    private fun part1(): Long {
        (0..Long.MAX_VALUE).forEach {
            val hash = md5Hash(it)
            if (hash.startsWith("00000")) return it
        }
        return -1
    }

    private fun part2(): Long {
        (0..Long.MAX_VALUE).forEach {
            val hash = md5Hash(it)
            if (hash.startsWith("000000")) return it
        }
        return -1
    }

    override fun toString(): String {
        return "Part1: ${part1()}, Part2: ${part2()}"
    }
}

private fun readInput(fileName: String) = Resources.resourceAsText("y2015/day4/$fileName.txt").trim()

fun main() {
    println(Day4(readInput("test1")))
    println(Day4(readInput("test2")))
    println(Day4(readInput("input")))
}
