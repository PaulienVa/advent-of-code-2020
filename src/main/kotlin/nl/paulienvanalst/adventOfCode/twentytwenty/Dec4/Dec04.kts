#!/usr/bin/env kscript

package nl.paulienvanalst.adventOfCode.twentytwenty.Dec4

import nl.paulienvanalst.adventOfCode.twentytwenty.utils.Utils

val input = Utils.readFileAsWhole()
val passports = input.split("\n\n")

val byr = "byr"
val iyr = "iyr"
val eyr = "eyr"
val hgt = "hgt"
val hcl = "hcl"
val ecl = "ecl"
val pid = "pid"

fun containsMandatoryFields(it: String) =
    (it.contains(byr) && it.contains(iyr) && it.contains(eyr)
            && it.contains(hgt) && it.contains(hcl) && it.contains(ecl)
            && it.contains(pid))

val minimalValidPassports = passports.filter {
    containsMandatoryFields(it)
}

println("Solution to part 1:")

println("Found ${minimalValidPassports.size} (182) valid passports in total ${passports.size}")


println("Solution to part 2:")

val validPassports = minimalValidPassports.map { it to validPassport(it) }.filter{ it.second }

//validPassports.forEach { println("Passport: ${it.first} and valid ${it.second} \n") }

println("Found ${validPassports.size} (28) valid passports in total ${passports.size}")


fun validPassport(passport: String) : Boolean = validBirthYear(passport) && validIssueYear(passport)
        && validExpirationYear(passport) && heightFieldValidation(passport) && hairlengthValidation(passport)
        && eyeColourValidation(passport) && passportIdValidation(passport)


fun validBirthYear(passport: String) = yearFieldValidation(passport, byr, 1920, 2002)
fun validIssueYear(passport: String) = yearFieldValidation(passport, iyr, 2010, 2020)
fun validExpirationYear(passport: String) = yearFieldValidation(passport, eyr, 2020, 2030)

fun yearFieldValidation(passport: String, field: String, lowerBoundary: Int, higherBoundary: Int): Boolean {
    val regex = "(?<=$field:)(\\d{4})".toRegex()
    val year = regex.find(passport)?.groupValues?.get(0)

    return year != null && year.toInt() in lowerBoundary .. higherBoundary
}

fun heightFieldValidation(passport: String): Boolean {
    val regex = "(?<=$hgt:)(\\d*)(?=cm|in)".toRegex()
    val height = regex.find(passport)?.groupValues?.get(0)
    val unitRegex = "(?<=$hgt:\\d*)(cm|in)".toRegex()
    val unit = unitRegex.find(passport)?.groupValues?.get(0)

    if (height == null || unit == null) {
        return false
    }

    val b = (unit == "cm" && height.toInt() in 150..193
            || unit == "in" && height.toInt() in 59..76)
//    println("Validating height $height in unit $unit and valid $b")
    return b
}

fun hairlengthValidation(passport: String): Boolean {
    val regex = "(?<=$hcl:)(#[0-9a-f]{6})".toRegex()
    val hairLength = regex.find(passport)?.groupValues?.get(0)

    val b = hairLength != null
//    println("valid hairlength: $hairLength and $b")
    return b
}

fun eyeColourValidation(passport: String) : Boolean {
    val regex = "(?<=$ecl:)([a-z]{3})".toRegex()
    val eyeColour = regex.find(passport)?.groupValues?.get(0)
    val validColours = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    val b = eyeColour != null && eyeColour in validColours

//    println("valid eyeColour: $eyeColour and valid $b")
    return b
}

fun passportIdValidation(passport: String) : Boolean {
    val regex = "(?<=($pid:))(\\d{9})".toRegex()
    val passportId = regex.find(passport)?.groupValues?.get(0)

//    println("Validating passportId: $passportId")
    return passportId != null
}
