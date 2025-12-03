package day3

import ProblemRunner
import java.io.File
import java.lang.StringBuilder
import java.util.*
import kotlin.math.max

object JoltageSolver : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day3/input.txt"

    override fun answerPartOne(input: File): String {
        val scanner = Scanner(input)
        var total = 0
        while (scanner.hasNextLine()) {
            val bank = scanner.nextLine()
            var bankMax = 0
            for (i in bank.indices) {
                val tensDigit = bank[i]
                for (j in i + 1..<bank.length) {
                    val onesDigit = bank[j]
                    val temp = "$tensDigit$onesDigit".toInt()
                    bankMax = max(bankMax, temp)
                }
            }
            total += bankMax
        }
        return "$total"
    }

    override fun answerPartTwo(input: File): String {
        val scanner = Scanner(input)
        var total = 0L
        while (scanner.hasNextLine()) {
            val bank = scanner.nextLine()
            val joltageStringBuilder = StringBuilder()
            var lastPosition = -1
            for (i in 0..11) {
                var currentDigitMax = 0
                val digitsLeft = 12 - i
                for (j in lastPosition+1..bank.length - digitsLeft) {
                    val potentialDigit = "${bank[j]}".toInt()
                    if (potentialDigit > currentDigitMax) {
                        lastPosition = j
                        currentDigitMax = potentialDigit
                    }
                }
                joltageStringBuilder.append(currentDigitMax)
            }
            total += joltageStringBuilder.toString().toLong()
        }
        return "$total"
    }
}

fun main() {
    println(JoltageSolver.answerPartOne(File(JoltageSolver.filePath)))
    println(JoltageSolver.answerPartTwo(File(JoltageSolver.filePath)))
}