package com.ridehailing.driver.models.api

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("driverData") val driverData: FetchDriverResponse
)
