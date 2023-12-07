package com.ridehailing.driver.models

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
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
) : Serializable {
    companion object {
        fun fromJson(json: JSONObject): TripInfo {

            val pickupAddressJson = JSONObject(json.getString("pickupAddress"))
            val destinationAddressJson = JSONObject(json.getString("destinationAddress"))

            return TripInfo(
                id = json.getString("id"),
                pickupAddress = Address(
                    pickupAddressJson.getString("address"),
                    pickupAddressJson.getDouble("latitude"),
                    pickupAddressJson.getDouble("longitude")
                ),
                destinationAddress = Address(
                    destinationAddressJson.getString("address"),
                    destinationAddressJson.getDouble("latitude"),
                    destinationAddressJson.getDouble("longitude")
                ),
                cost = json.getInt("cost"),
                distanceInKilometers = json.getDouble("distanceInKilometers"),
                durationInMinutes = json.getInt("durationInMinutes"),
                minutesToDriverArrival = json.getInt("minutesToDriverArrival"),
                kilometersToDriverArrival = json.getDouble("kilometersToDriverArrival"),
                paymentMethod = json.getString("paymentMethod"),
                noteForDriver = json.getString("noteForDriver"),
                userInfo = UserInfo(
                    userName = json.getJSONObject("userInfo").getString("userName"),
                    phoneNumber = json.getJSONObject("userInfo").getString("phoneNumber")
                )
            )
        }
    }
}
