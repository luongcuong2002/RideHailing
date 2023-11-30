package com.cuongnl.ridehailing.models.api

import com.cuongnl.ridehailing.models.Notification
import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("notifications") val notifications: List<Notification>
)
