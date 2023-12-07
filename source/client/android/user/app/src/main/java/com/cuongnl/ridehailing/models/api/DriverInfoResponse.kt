package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable

data class DriverInfoResponse(
    @SerializedName("id") val id: String,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("driverAvatar") val driverAvatar: String?,
    @SerializedName("licensePlate") val licensePlate: String,
    @SerializedName("vehicleBrand") val vehicleBrand: String,
    @SerializedName("travelMode") val travelMode: String,
    @SerializedName("currentLatitude") val currentLatitude: Double,
    @SerializedName("currentLongitude") val currentLongitude: Double,
    @SerializedName("totalRating") val totalRating: Double,
) : Serializable {
    companion object {
        fun fromJson(json: JSONObject): DriverInfoResponse {
            return DriverInfoResponse(
                id = json.getString("id"),
                driverName = json.getString("driverName"),
                phoneNumber = json.getString("phoneNumber"),
                driverAvatar = json.getString("driverAvatar"),
                licensePlate = json.getString("licensePlate"),
                vehicleBrand = json.getString("vehicleBrand"),
                travelMode = json.getString("travelMode"),
                currentLatitude = json.getDouble("currentLatitude"),
                currentLongitude = json.getDouble("currentLongitude"),
                totalRating = json.getDouble("totalRating"),
            )
        }
    }
}
