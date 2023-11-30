package com.ridehailing.driver.network.retrofit.repository

import com.ridehailing.driver.models.api.LoginRequest
import com.ridehailing.driver.models.api.LoginResponse
import com.ridehailing.driver.network.retrofit.RetrofitClient
import com.ridehailing.driver.network.retrofit.api.AuthApi
import retrofit2.Callback

class AuthRepository {

    private val authApi: AuthApi = RetrofitClient.getClient().create(AuthApi::class.java)

    fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>) {
        authApi.login(loginRequest).enqueue(callback)
    }
}