package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("id") val id : Int,
    @SerializedName("date") val date : String,
    @SerializedName("title") val title : String,
    @SerializedName("shortContent") val shortContent : String,
    @SerializedName("content") val content : String,
    @SerializedName("image") val base64Image : String,
    @SerializedName("isRead") val isRead : Boolean
)
