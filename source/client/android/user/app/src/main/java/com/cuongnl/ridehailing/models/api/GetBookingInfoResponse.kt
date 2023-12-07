package com.cuongnl.ridehailing.models.api

import com.cuongnl.ridehailing.models.DriverInfo
import com.google.gson.annotations.SerializedName

data class GetBookingInfoResponse(
    @SerializedName("fareAmount") val fareAmount: Int,
    @SerializedName("fareCalculationInfo") val fareCalculationInfo: String,
    @SerializedName("minutesToDriverArrival") val minutesToDriverArrival: Int,
    @SerializedName("kilometersToDriverArrival") val kilometersToDriverArrival: Double,
    @SerializedName("driversNearbyLocation") val driversNearbyLocation: List<DriverInfo>,
)
