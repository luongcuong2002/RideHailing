package com.ridehailing.driver.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TripInfo (
    @SerializedName("id") val id: String,
    @SerializedName("pickupAddress") val pickupAddress: Address,
    @SerializedName("destinationAddress") val destinationAddress: Address,
    @SerializedName("distanceInKilometers") val distanceInKilometers: Double,
    @SerializedName("durationInMinutes") val durationInMinutes: Int,
    @SerializedName("minutesToDriverArrival") val minutesToDriverArrival: Int,
    @SerializedName("kilometersToDriverArrival") val kilometersToDriverArrival: Double,
    @SerializedName("paymentMethod") val paymentMethod: String,
    @SerializedName("noteForDriver") val noteForDriver: String,
    @SerializedName("cost") val cost: Int,
    @SerializedName("userInfo") val userInfo: UserInfo
) : Serializable
