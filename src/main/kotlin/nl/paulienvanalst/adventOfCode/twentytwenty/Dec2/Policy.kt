package nl.paulienvanalst.adventOfCode.twentytwenty.Dec2

data class Policy(val minOcc: Int, val maxOcc: Int, val character: String) {
    companion object {
        private val regexMinOcc = "\\b(0?[1-9]|1[0-9]|2[0-5])\\b(?=-)".toRegex()
        private val regexForMaxOcc = "\\b(0?[1-9]|1[0-9]|2[0-5])\\b(?!-)".toRegex()
        private val regexChar = "[a-zA-Z](?!\\s)".toRegex()

        fun from(policy: String) : Policy {
            return Policy(
                regexMinOcc.find(policy)!!.groupValues[0].toInt(),
                regexForMaxOcc.find(policy)!!.groupValues[0].toInt(),
                regexChar.find(policy)!!.groupValues[0]
            )
        }
    }

    fun matchesFirstPolicy(password: String) : Boolean {
        return password.count { it.toString() == character } in minOcc .. maxOcc
    }

    fun matchesSecondPolicy(password: String) : Boolean {
        val occursInFirst = password[minOcc].toString() == character
        val occursInSecond = password[maxOcc].toString() == character

        if (occursInFirst && occursInSecond) {
            return false
        }

        return occursInFirst || occursInSecond
    }
}
