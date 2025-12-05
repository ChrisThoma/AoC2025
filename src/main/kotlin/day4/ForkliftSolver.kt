package day4

import ProblemRunner
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

object ForkliftSolver : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day4/input.txt"

    override fun answerPartOne(input: File): String {
        val scanner = Scanner(input)
        val grid = arrayListOf<CharArray>()
        grid.add(scanner.nextLine().toCharArray())
        grid.add(scanner.nextLine().toCharArray())
        val firstRow = 0
        var count = 0
        for (j in 0..<grid[firstRow].size) {
            if (grid[firstRow][j] == '@' &&
                countNeighbors(firstRow, j, grid) < 4
            ) {
                count++
            }
        }
        while (scanner.hasNextLine()) {
            grid.add(scanner.nextLine().toCharArray())
            if (grid.size == 3) {
                val mid = 1
                for (j in 0..<grid[mid].size) {
                    if (grid[mid][j] == '@' && countNeighbors(mid, j, grid) < 4) {
                        count++
                    }
                }
                grid.removeAt(0)
            }
        }
        val lastRow = 1
        for (j in 0..<grid[lastRow].size) {
            if (grid[lastRow][j] == '@' &&
                countNeighbors(lastRow, j, grid) < 4
            ) {
                count++
            }
        }

        return "$count"
    }

    private fun countNeighbors(row: Int, col: Int, grid: ArrayList<CharArray>): Int {
        var count = 0
        if (row - 1 >= 0 && grid[row - 1][col] == '@') count++
        if (row - 1 >= 0 && col - 1 >= 0 && grid[row - 1][col - 1] == '@') count++
        if (row - 1 >= 0 && col + 1 < grid[row - 1].size && grid[row - 1][col + 1] == '@') count++
        if (row + 1 < grid.size && grid[row + 1][col] == '@') count++
        if (row + 1 < grid.size && col - 1 >= 0 && grid[row + 1][col - 1] == '@') count++
        if (row + 1 < grid.size && col + 1 < grid[row + 1].size && grid[row + 1][col + 1] == '@') count++
        if (col - 1 >= 0 && grid[row][col - 1] == '@') count++
        if (col + 1 < grid[row].size && grid[row][col + 1] == '@') count++
        return count
    }


    override fun answerPartTwo(input: File): String {
        val scanner = Scanner(input)
        val grid = arrayListOf<CharArray>()
        while (scanner.hasNextLine()) {
            grid.add(scanner.nextLine().toCharArray())
        }
        var count = 0
        var tpRemoved = true
        while (tpRemoved) {
            tpRemoved = false
            for (i in 0..<grid.size) {
                for (j in 0..<grid[i].size) {
                    if (grid[i][j] == '@' &&
                        countNeighbors(i, j, grid) < 4
                    ) {
                        grid[i][j] = 'x'
                        count++
                        tpRemoved = true
                    }
                }
            }
        }
        return "$count"
    }
}

fun main() {
    println(ForkliftSolver.answerPartOne(File(ForkliftSolver.filePath)))
    println(ForkliftSolver.answerPartTwo(File(ForkliftSolver.filePath)))
}