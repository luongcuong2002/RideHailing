package com.cuongnl.ridehailing.network.retrofit.repository

import com.cuongnl.ridehailing.models.api.LoginRequest
import com.cuongnl.ridehailing.models.api.LoginResponse
import com.cuongnl.ridehailing.models.api.RegisterRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import com.cuongnl.ridehailing.network.retrofit.RetrofitClient
import com.cuongnl.ridehailing.network.retrofit.api.AuthApi
import retrofit2.Callback

class AuthRepository {

    private val authApi: AuthApi = RetrofitClient.getClient().create(AuthApi::class.java)

    fun checkExistingUser(phoneNumber: String, callback: Callback<ScalarsBooleanResponse>) {
        authApi.checkExistingUser(phoneNumber).enqueue(callback)
    }

    fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>) {
        authApi.login(loginRequest).enqueue(callback)
    }

    fun register(registerRequest: RegisterRequest, callback: Callback<ScalarsBooleanResponse>) {
        authApi.register(registerRequest).enqueue(callback)
    }
}