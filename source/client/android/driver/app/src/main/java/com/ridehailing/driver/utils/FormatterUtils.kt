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

    fun getShortAddress(fullAddress: String): String {
        val commaCount = 3
        var commaIndex = -1
        var foundCount = 0

        for (i in fullAddress.indices) {
            if (fullAddress[i] == ',') {
                foundCount++
                if (foundCount == commaCount) {
                    commaIndex = i
                    break
                }
            }
        }

        return if (commaIndex != -1) {
            fullAddress.substring(0, commaIndex)
        } else {
            fullAddress
        }
    }
}