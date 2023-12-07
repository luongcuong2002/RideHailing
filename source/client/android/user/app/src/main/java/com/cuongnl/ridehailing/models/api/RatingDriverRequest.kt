package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName

data class RatingDriverRequest(
    @SerializedName("driverId") val driverId: String,
    @SerializedName("star") val star: Int
)
