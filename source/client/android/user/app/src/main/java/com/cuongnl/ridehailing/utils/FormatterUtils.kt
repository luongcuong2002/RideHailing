package com.cuongnl.ridehailing.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil

object FormatterUtils {
    fun formatPhoneNumberToInternationalFormation(
        phoneNumber: String,
        countryCode: String
    ): String {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val parsedNumber = phoneNumberUtil.parse(phoneNumber, countryCode)
            return phoneNumberUtil.format(
                parsedNumber,
                PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return phoneNumber
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