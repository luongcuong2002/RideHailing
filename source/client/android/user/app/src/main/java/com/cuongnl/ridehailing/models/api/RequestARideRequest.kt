package com.cuongnl.ridehailing.models.api

import com.cuongnl.ridehailing.enums.PaymentMethod
import com.cuongnl.ridehailing.enums.TransportationType
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

data class RequestARideRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("destinationLatitude") val destinationLatitude: Double,
    @SerializedName("destinationLongitude") val destinationLongitude: Double,
    @SerializedName("destinationAddress") val destinationAddress: String,
    @SerializedName("pickupLatitude") val pickupLatitude: Double,
    @SerializedName("pickupLongitude") val pickupLongitude: Double,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("paymentMethod") val paymentMethod: PaymentMethod,
    @SerializedName("noteForDriver") val noteForDriver: String,
    @SerializedName("travelMode") val transportationType: TransportationType,
    @SerializedName("distanceInKilometers") val distanceInKilometers: Double,
    @SerializedName("durationInMinutes") val durationInMinutes: Int,
    @SerializedName("kilometersToDriverArrival") val kilometersToDriverArrival: Double,
    @SerializedName("minutesToDriverArrival") val minutesToDriverArrival: Int,
    @SerializedName("cost") val cost: Int,
    @SerializedName("driverSocketIds") val driverSocketIds: List<String>
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

        data.put("destinationAddress", destination.toString())
        data.put("pickupAddress", pickup.toString())

        data.put("phoneNumber", phoneNumber)
        data.put("paymentMethod", paymentMethod.name)
        data.put("noteForDriver", noteForDriver)
        data.put("travelMode", transportationType.name)
        data.put("distanceInKilometers", distanceInKilometers)
        data.put("durationInMinutes", durationInMinutes)
        data.put("kilometersToDriverArrival", kilometersToDriverArrival)
        data.put("minutesToDriverArrival", minutesToDriverArrival)
        data.put("cost", cost)

        val driverSocketIdsJsonArray = JSONArray()

        for (driverSocketId in driverSocketIds) {
            val json = JSONObject()
            json.put("socketId", driverSocketId)
            driverSocketIdsJsonArray.put(json)
        }

        data.put("driverSocketIds", driverSocketIdsJsonArray)

        return data
    }
}
