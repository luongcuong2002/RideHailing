package com.cuongnl.ridehailing.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    fun convertTimestampToString(timestamp: Long): String {
        val date = Date(timestamp)
        val format = SimpleDateFormat("HH:mm, dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }
}