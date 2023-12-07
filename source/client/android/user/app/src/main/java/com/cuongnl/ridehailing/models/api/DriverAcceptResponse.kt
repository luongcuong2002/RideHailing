package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable

data class DriverAcceptResponse(
    @SerializedName("tripId") val tripId: String,
    @SerializedName("driverInfo") val driverInfo: DriverInfoResponse,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("pickupLatitude") val pickupLatitude: Double,
    @SerializedName("pickupLongitude") val pickupLongitude: Double,
    @SerializedName("destinationAddress") val destinationAddress: String,
    @SerializedName("destinationLatitude") val destinationLatitude: Double,
    @SerializedName("destinationLongitude") val destinationLongitude: Double,
    @SerializedName("minutesToDriverArrival") val minutesToDriverArrival: Int
) : Serializable {
    companion object {
        fun fromJson(json: JSONObject) : DriverAcceptResponse {
            val driverInfo = DriverInfoResponse.fromJson(JSONObject(json.getString("driverInfo")))

            return DriverAcceptResponse(
                json.getString("tripId"),
                driverInfo,
                json.getString("pickupAddress"),
                json.getDouble("pickupLatitude"),
                json.getDouble("pickupLongitude"),
                json.getString("destinationAddress"),
                json.getDouble("destinationLatitude"),
                json.getDouble("destinationLongitude"),
                json.getInt("minutesToDriverArrival"),
            )
        }
    }
}
