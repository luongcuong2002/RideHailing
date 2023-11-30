package com.ridehailing.driver.models.api

import com.google.gson.annotations.SerializedName

data class FetchDriverResponse(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("driverAvatar") val driverAvatar: String?,
    @SerializedName("licensePlate") val licensePlate: String,
    @SerializedName("vehicleBrand") val vehicleBrand: String,
    @SerializedName("vehicleType") val vehicleType: String,
)