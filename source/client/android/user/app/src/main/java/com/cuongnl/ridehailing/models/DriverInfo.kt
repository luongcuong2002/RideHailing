package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class DriverInfo(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("socketId") val socketId: String,
)
