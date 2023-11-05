package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("email") val email: String?,
)