#!/usr/bin/env kscript

package nl.paulienvanalst.adventOfCode.twentytwenty.Dec4

import nl.paulienvanalst.adventOfCode.twentytwenty.utils.Utils

val passports = Utils.readFileAsWhole().split("\n\n").map { it.replace("\n", " ") }

val mandatoryFields = listOf(
    "byr:",
    "iyr:",
    "eyr:",
    "hgt:",
    "hcl:",
    "ecl:",
    "pid:"
)

val minimalValidPassports = passports.filter { passport ->
    mandatoryFields.all { passport.contains(it) }
}

println("Solution to part 1:")

println("Found ${minimalValidPassports.size} (182) valid passports in total ${passports.size}")

println("Solution to part 2:")

val validPassports = minimalValidPassports.map { it to validPassport(it) }.filter { it.second }

println("Found ${validPassports.size} (110, not the correct one) valid passports in total ${passports.size}")

fun validPassport(passport: String) = validations().all { it(passport) }

fun validations() : List<(String) -> Boolean> = listOf(
    {passport -> validBirthYear(passport)},
    {passport -> validIssueYear(passport)},
    {passport -> validExpirationYear(passport)},
    {passport -> heightFieldValidation(passport)},
    {passport -> hairlengthValidation(passport)},
    {passport -> eyeColourValidation(passport)},
    {passport -> passportIdValidation(passport)}
)

fun validBirthYear(passport: String) = yearFieldValidation(passport, "byr", 1920, 2002)
fun validIssueYear(passport: String) = yearFieldValidation(passport, "iyr", 2010, 2020)
fun validExpirationYear(passport: String) = yearFieldValidation(passport, "eyr", 2020, 2030)

fun yearFieldValidation(passport: String, field: String, lowerBoundary: Int, higherBoundary: Int): Boolean {
    val regex = "(?<=$field:)(\\d{4})".toRegex()
    val year = regex.find(passport)?.groupValues?.get(0)

    return year != null && year.toInt() in lowerBoundary..higherBoundary
}

fun heightFieldValidation(passport: String): Boolean {
    val regex = "(?<=hgt:)(\\d*)(?=cm|in)".toRegex()
    val height = regex.find(passport)?.groupValues?.get(0)
    val unitRegex = "(?<=hgt:\\d*)(cm|in)".toRegex()
    val unit = unitRegex.find(passport)?.groupValues?.get(0)

    if (height == null || unit == null) {
        return false
    }

    return (unit == "cm" && height.toInt() in 150..193
            || unit == "in" && height.toInt() in 59..76)
}

fun hairlengthValidation(passport: String): Boolean {
    val regex = "(?<=hcl:)(\\#[0-9a-f]{6})".toRegex()
    val hairLength = regex.find(passport)?.groupValues?.get(0)

    return hairLength != null
}

fun eyeColourValidation(passport: String): Boolean {
    val regex = "(?<=ecl:)([a-z]{3})".toRegex()
    val eyeColour = regex.find(passport)?.groupValues?.get(0)
    val validColours = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    return eyeColour != null && eyeColour in validColours
}

fun passportIdValidation(passport: String): Boolean {
    val regex = "(?<=(pid:))(\\d{9})".toRegex()
    val passportId = regex.find(passport)?.groupValues?.get(0)

    return passportId != null
}
