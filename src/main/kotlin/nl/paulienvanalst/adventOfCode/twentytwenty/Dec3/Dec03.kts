#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwenty.utils.Utils
import java.math.BigInteger

val input = Utils.readInput()

val nrOfLines = input.size
val sizeOfOneLine = input[0].length

fun countTrees(incrementRight: Int, incrementDown: Int) : Int {
    val tree = '#'

    var y = incrementDown
    var x = incrementRight

    var nrOfTrees = 0
    while (y < nrOfLines) {
        if (input[y][x] == tree){
            nrOfTrees += 1
        }
        x = (x + incrementRight) % sizeOfOneLine
        y += incrementDown
    }
    println("found trees $nrOfTrees")
    return nrOfTrees
}

println("Solution to part 1:")


val nrOfTreesOnFirstSlope = countTrees(3, 1)

println("Found $nrOfTreesOnFirstSlope (274) trees on the slope")

println("Solution to part 2:")

val slopes = listOf(
    1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2
)

val nrOfTreesOnSlopes = slopes.map {
    countTrees(it.first, it.second)
}.map {
    BigInteger("$it")
}.reduce { acc, it -> acc * it }

println("Found $nrOfTreesOnSlopes (6050183040) on all slopes")
