package com.ridehailing.driver.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName("address") val address: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
) : Serializable
