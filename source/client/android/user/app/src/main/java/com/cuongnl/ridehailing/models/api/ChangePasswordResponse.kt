package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("error") val error: String,
)