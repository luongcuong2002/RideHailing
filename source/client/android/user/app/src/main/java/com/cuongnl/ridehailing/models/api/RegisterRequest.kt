package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String,
)
