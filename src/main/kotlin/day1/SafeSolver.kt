package day1

import ProblemRunner
import java.io.File
import java.util.*

object SafeSolver : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day1/input.txt"

    override fun answerPartOne(input: File): String {
        val scanner = Scanner(input)
        var zeroCount = 0
        var position = 50
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val isLeft = line[0] == 'L'
            val distance = line.substring(1).toInt()
            position = if (isLeft) {
                (position - distance) % 100
            } else {
                (position + distance) % 100
            }
            if (position == 0) {
                zeroCount++
            }
        }
        return "$zeroCount"
    }

    override fun answerPartTwo(input: File): String {
        val scanner = Scanner(input)
        var zeroCount = 0
        var position = 50
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val isLeft = line[0] == 'L'
            val distance = line.substring(1).toInt()
            for (i in 0..< distance) {
                if (isLeft) {
                    position--
                } else {
                    position++
                }
                if (position == -1) {
                    position = 99
                } else {
                    position %= 100
                }

                if (position == 0) {
                    zeroCount++
                }
            }
        }
        return "$zeroCount"
    }
}

fun main() {
    val file = File("src/main/kotlin/day1/input.txt")
    println(SafeSolver.answerPartOne(file))
    println(SafeSolver.answerPartTwo(file))
}