package com.cuongnl.ridehailing.utils

object CheckerUtils {
    fun isOnlyNumbers(input: String): Boolean {
        val regex = "^[0-9]*\$"
        return input.matches(regex.toRegex())
    }
}