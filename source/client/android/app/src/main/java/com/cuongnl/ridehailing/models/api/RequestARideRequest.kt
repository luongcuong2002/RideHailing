package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

data class RequestARideRequest(
    @SerializedName("destinationLatitude") val destinationLatitude: Double,
    @SerializedName("destinationLongitude") val destinationLongitude: Double,
    @SerializedName("destinationAddress") val destinationAddress: String,
    @SerializedName("pickupLatitude") val pickupLatitude: Double,
    @SerializedName("pickupLongitude") val pickupLongitude: Double,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("paymentMethod") val paymentMethod: String,
    @SerializedName("noteForDriver") val noteForDriver: String,
    @SerializedName("travelMode") val travelMode: String,
    @SerializedName("distanceInKilometers") val distanceInKilometers: Double,
    @SerializedName("durationInMinutes") val durationInMinutes: Int,
    @SerializedName("kilometersToDriverArrival") val kilometersToDriverArrival: Double,
    @SerializedName("minutesToDriverArrival") val minutesToDriverArrival: Int,
    @SerializedName("cost") val cost: Int,
) : Serializable {
    fun toJson() : JSONObject {
        val data = JSONObject()

        val destination = JSONObject()
        destination.put("latitude", destinationLatitude)
        destination.put("longitude", destinationLongitude)
        destination.put("address", destinationAddress)

        val pickup = JSONObject()
        pickup.put("latitude", pickupLatitude)
        pickup.put("longitude", pickupLongitude)
        pickup.put("address", pickupAddress)

        data.put("destination", destination.toString())
        data.put("pickup", pickup.toString())

        data.put("paymentMethod", paymentMethod)
        data.put("noteForDriver", noteForDriver)
        data.put("travelMode", travelMode)
        data.put("distanceInKilometers", distanceInKilometers)
        data.put("durationInMinutes", durationInMinutes)
        data.put("kilometersToDriverArrival", kilometersToDriverArrival)
        data.put("minutesToDriverArrival", minutesToDriverArrival)
        data.put("cost", cost)

        return data
    }
}
