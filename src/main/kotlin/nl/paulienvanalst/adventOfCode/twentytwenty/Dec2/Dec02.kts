#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwenty.utils.Utils
import nl.paulienvanalst.adventOfCode.twentytwenty.Dec2.Policy

val input = Utils.readInput()

val policyAndPassword = input.map {
    it.splitToSequence(':').toList()
}.map {
    Policy.from(it[0]) to it[1]
}

println("Solution to part 1:")
val correctPasswords = policyAndPassword.count {
    it.first.matchesFirstPolicy(it.second)
}

println("Found correct passwords $correctPasswords (424) in total of ${input.size}")

println("Solution to part 2:")

val correctPassword2 = policyAndPassword.count {
    it.first.matchesSecondPolicy(it.second)
}

println("Found correct passwords $correctPassword2 (747) in total of ${input.size}")

