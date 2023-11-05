package com.cuongnl.ridehailing.retrofit.repository

import com.cuongnl.ridehailing.models.AddressResponse
import com.cuongnl.ridehailing.models.ChangePasswordRequest
import com.cuongnl.ridehailing.models.ChangePasswordResponse
import com.cuongnl.ridehailing.models.GetUserResponse
import com.cuongnl.ridehailing.models.NotificationResponse
import com.cuongnl.ridehailing.retrofit.RetrofitClient
import com.cuongnl.ridehailing.retrofit.api.UserApi
import retrofit2.Callback

class UserRepository {

    private val userApi: UserApi = RetrofitClient.getClient().create(UserApi::class.java)

    fun changePassword(changePasswordRequest: ChangePasswordRequest, callback: Callback<ChangePasswordResponse>) {
        userApi.changePassword(changePasswordRequest).enqueue(callback)
    }

    fun getUser(callback: Callback<GetUserResponse>) {
        userApi.getUser().enqueue(callback)
    }

    fun getUserAddresses(callback: Callback<AddressResponse>) {
        userApi.getUserAddresses().enqueue(callback)
    }

    fun getNotifications(callback: Callback<NotificationResponse>) {
        userApi.getNotifications().enqueue(callback)
    }
}