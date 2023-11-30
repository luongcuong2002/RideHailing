package com.cuongnl.ridehailing.network.retrofit.api

import com.cuongnl.ridehailing.models.api.NotificationResponse
import com.cuongnl.ridehailing.models.api.RemoveNotificationsRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationApi {
    @GET("notification/get-notifications")
    fun getNotifications() : Call<NotificationResponse>

    @POST("notification/remove-all-notifications")
    fun removeAllNotification() : Call<ScalarsBooleanResponse>

    @POST("notification/remove-notifications")
    fun removeNotifications(@Body request: RemoveNotificationsRequest) : Call<ScalarsBooleanResponse>
}