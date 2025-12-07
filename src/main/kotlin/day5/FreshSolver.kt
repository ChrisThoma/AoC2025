package day5

import ProblemRunner
import java.io.File
import java.util.*

object FreshSolver : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day5/input.txt"

    override fun answerPartOne(input: File): String {
        val scanner = Scanner(input)
        val ranges = arrayListOf<LongRange>()
        var scannedLine = scanner.nextLine()
        while (scannedLine != "") {
            val rangeArray = scannedLine.split('-')
            ranges.add(LongRange(rangeArray[0].toLong(), rangeArray[1].toLong()))
            scannedLine = scanner.nextLine()
        }

        var count = 0
        while (scanner.hasNextLine()) {
            val foodId = scanner.nextLine().toLong()
            for (range in ranges) {
                if (foodId in range) {
                    count++
                    break
                }
            }
        }
        return "$count"
    }

    override fun answerPartTwo(input: File): String {
        val scanner = Scanner(input)
        val ranges = arrayListOf<LongRange>()
        var scannedLine = scanner.nextLine()
        while (scannedLine != "") {
            val rangeArray = scannedLine.split('-')
            ranges.add(LongRange(rangeArray[0].toLong(), rangeArray[1].toLong()))
            scannedLine = scanner.nextLine()
        }
        ranges.sortBy { it.first }
        val combinedRanges = arrayListOf<LongRange>()
        combinedRanges.add(ranges[0])
        for (i in ranges.indices) {
            if (ranges[i].first in combinedRanges.last()) {
                val min = ranges[i].first.coerceAtMost(combinedRanges.last().first)
                val max = ranges[i].last.coerceAtLeast(combinedRanges.last().last)
                combinedRanges.removeLast()
                combinedRanges.add(LongRange(min, max))
            } else {
                combinedRanges.add(ranges[i])
            }
        }
        var total = 0L
        for (range in combinedRanges) {
            println("${range.first}, ${range.last}")
            total += (range.last - range.first) + 1
        }
        return "$total"
    }
}

fun main() {
    println(FreshSolver.answerPartOne(File(FreshSolver.filePath)))
    println(FreshSolver.answerPartTwo(File(FreshSolver.filePath)))
}