package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class SerializedLatLng(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
)
