package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName

data class GetBookingInfoRequest(
    @SerializedName("travelMode") val travelMode: String,
    @SerializedName("startLatitude") val startLatitude: Double,
    @SerializedName("startLongitude") val startLongitude: Double,
    @SerializedName("endLatitude") val endLatitude: Double,
    @SerializedName("endLongitude") val endLongitude: Double,
    @SerializedName("distanceInKilometers") val distanceInKilometers: Double,
)
