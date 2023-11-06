package com.cuongnl.ridehailing.viewmodel.apiservice

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.callbacks.api.RetrofitCallback
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.models.api.NotificationResponse
import com.cuongnl.ridehailing.retrofit.repository.NotificationRepository
import retrofit2.Call
import retrofit2.Response

class NotificationServiceViewModel : ViewModel() {

    private var notificationRepository = NotificationRepository()

    fun getNotifications(simpleApiCallback: SimpleApiCallback<NotificationResponse>) {
        notificationRepository.getNotifications(object : RetrofitCallback<NotificationResponse>(simpleApiCallback) {
            override fun onResponse(
                call: Call<NotificationResponse>,
                response: Response<NotificationResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    simpleApiCallback.onSuccess(call, response)
                }
            }
        })
    }
}