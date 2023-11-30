package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("newPassword") val newPassword: String,
)
