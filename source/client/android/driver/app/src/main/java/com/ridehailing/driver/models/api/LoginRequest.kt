package com.ridehailing.driver.models.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
)
