package com.cuongnl.ridehailing.models.api

import com.cuongnl.ridehailing.models.Address
import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("addresses") val addresses: List<Address>,
)
