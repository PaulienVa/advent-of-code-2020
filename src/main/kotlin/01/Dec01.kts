#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwenty.utils.Utils

val reportInput = Utils.readInput().map { it.toInt() }
val nrOfInputs = reportInput.size

println("Solution to part 1:")
// imperative code
reportInput.forEach {
    var index = 0
    while (index < nrOfInputs - 1 && it + reportInput[index] != 2020) {
        index += 1
    }
    if (it + reportInput[index] == 2020 ) {
        println("Found the answer! ${it * reportInput[index]} and it is equal to 494475")
    }
}

val valueAndDifference = reportInput.map { it to 2020 - it }.toMap()
valueAndDifference.keys.find { it in valueAndDifference.values }.run {
    println("Found the answer! $this and ${2020 - this!!} and it is equal to ${this * (2020 - this)}")
}

println("Solution to part 2:")

val sortedInput = reportInput.sorted()

val halfway = nrOfInputs / 2

(0 .. halfway).forEach { index ->
    var stepUp = index + 1
    var stepDown = nrOfInputs - 1

    while (stepUp < stepDown) {
        val sum = sortedInput[stepUp] + sortedInput[stepDown] + sortedInput[index]

        if( sum == 2020) {
            val multiply = sortedInput[stepUp] * sortedInput[stepDown] * sortedInput[index]
            println("Outcomes params: sum $sum indexOutcome: ${sortedInput[index]} stepUpOutcome: ${sortedInput[stepUp]} stepDownOutcome: ${sortedInput[stepDown]}")
            println("Found the answer! $multiply which is equal to 267520550")
            break
        }

        stepDown = if (sum > 2020) stepDown - 1 else stepDown
        stepUp = if (sum < 2020) stepUp + 1 else stepUp
    }
}



