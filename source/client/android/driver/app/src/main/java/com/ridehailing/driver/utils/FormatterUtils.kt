package com.ridehailing.driver.utils

object FormatterUtils {
    fun formatCurrency(amount: Int): String {
        val amountStr = amount.toString().reversed()

        val formattedStr = StringBuilder()
        for (i in amountStr.indices) {
            formattedStr.append(amountStr[i])
            if ((i + 1) % 3 == 0 && i != amountStr.lastIndex) {
                formattedStr.append(".")
            }
        }

        return formattedStr.reverse().toString()
    }
}