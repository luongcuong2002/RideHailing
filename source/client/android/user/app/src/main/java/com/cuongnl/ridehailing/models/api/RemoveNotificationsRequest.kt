package com.cuongnl.ridehailing.models.api

import com.google.gson.annotations.SerializedName

data class RemoveNotificationsRequest(
    @SerializedName("notificationIds") val notificationIds: List<Int>,
)
