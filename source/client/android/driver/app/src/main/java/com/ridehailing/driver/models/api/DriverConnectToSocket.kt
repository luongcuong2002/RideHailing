package com.ridehailing.driver.models.api

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable

data class DriverConnectToSocket(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("currentLatitude") val currentLatitude: Double,
    @SerializedName("currentLongitude") val currentLongitude: Double,
) : Serializable {
    fun toJson() : JSONObject {
        val data = JSONObject()

        data.put("phoneNumber", phoneNumber)
        data.put("currentLatitude", currentLatitude)
        data.put("currentLongitude", currentLongitude)

        return data
    }
}
