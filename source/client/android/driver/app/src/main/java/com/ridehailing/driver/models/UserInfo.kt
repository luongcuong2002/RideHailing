package com.ridehailing.driver.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfo(
    @SerializedName("userName") val userName: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
) : Serializable
