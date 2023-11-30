package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R
import com.google.gson.annotations.SerializedName

enum class PaymentMethod(val icon: Int, val title: Int) {
    @SerializedName("cash")
    CASH(R.drawable.ic_cash, R.string.cash_text),
}