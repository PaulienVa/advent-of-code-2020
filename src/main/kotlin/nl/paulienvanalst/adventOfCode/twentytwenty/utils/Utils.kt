package nl.paulienvanalst.adventOfCode.twentytwenty.utils

import java.io.File

object Utils {
    fun readInput() = File("./input.txt").inputStream().bufferedReader().readLines()
    fun readFileAsWhole() = File("./input.txt").inputStream().bufferedReader().readText()
}
