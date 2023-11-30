package com.cuongnl.ridehailing.network.retrofit.repository

import com.cuongnl.ridehailing.models.api.AddressResponse
import com.cuongnl.ridehailing.models.api.ChangePasswordRequest
import com.cuongnl.ridehailing.models.api.ChangePasswordResponse
import com.cuongnl.ridehailing.models.api.GetUserResponse
import com.cuongnl.ridehailing.models.api.NotificationResponse
import com.cuongnl.ridehailing.network.retrofit.RetrofitClient
import com.cuongnl.ridehailing.network.retrofit.api.UserApi
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
}