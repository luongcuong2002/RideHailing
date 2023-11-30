package com.cuongnl.ridehailing.models

import com.cuongnl.ridehailing.enums.AddressType
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("addressType") val addressType: AddressType,
    @SerializedName("shortName") val shortName: String,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
)
