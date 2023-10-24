package com.cuongnl.ridehailing.retrofit.repository

import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import com.cuongnl.ridehailing.retrofit.RetrofitClient
import com.cuongnl.ridehailing.retrofit.api.AuthApi
import retrofit2.Callback

class AuthRepository {

    private val authApi : AuthApi = RetrofitClient.getClient().create(AuthApi::class.java)

    fun checkExistingUser(numberPhone: String, callback: Callback<ScalarsBooleanResponse>) {
        authApi.checkExistingUser(numberPhone).enqueue(callback)
    }
}