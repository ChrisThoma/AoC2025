package day2

import ProblemRunner
import java.io.File
import java.util.*

object InvalidIdSolver : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day2/input.txt"

    override fun answerPartOne(input: File): String {
        val scanner = Scanner(input)
        val ranges = scanner.nextLine().split(',')
        var total: Long = 0L
        for (range in ranges) {
            val pair = range.split('-')
            val low = pair[0]
            val high = pair[1]
            val distance = high.toLong() - low.toLong()
            for (i in 0..distance) {
                val current = low.toLong() + i
                val currentString = current.toString()
                val currentLength = current.toString().length
                var repeatPos = currentLength / 2
                var isInvalid = true
                if (currentLength % 2 != 0) { continue }
                for (j in 0..<currentLength / 2) {
                    var k = repeatPos
                    if (currentString[j] != currentString[k]) {
                        isInvalid = false
                        break
                    }
                    repeatPos++
                }
                if (isInvalid) {
                    total += current
                }
            }
        }
        return "$total"
    }

    override fun answerPartTwo(input: File): String {
        val scanner = Scanner(input)
        val ranges = scanner.nextLine().split(',')
        var total: Long = 0L
        for (range in ranges) {
            val pair = range.split('-')
            val low = pair[0]
            val high = pair[1]
            val distance = high.toLong() - low.toLong()
            for (i in 0..distance) {
                val current = low.toLong() + i
                val currentString = current.toString()
                if (isRepeatedPattern(currentString)) {
                    total += current
                }
            }
        }
        return "$total"
    }

    private fun isRepeatedPattern(s: String): Boolean {
        val n = s.length

        for (len in 1..n/2) {
            if (n % len == 0) {
                val pattern = s.substring(0, len)
                val repeats = n / len

                if (pattern.repeat(repeats) == s) {
                    return true
                }
            }
        }
        return false
    }

}

fun main() {
    println(InvalidIdSolver.answerPartOne(File(InvalidIdSolver.filePath)))
    println(InvalidIdSolver.answerPartTwo(File(InvalidIdSolver.filePath)))
}