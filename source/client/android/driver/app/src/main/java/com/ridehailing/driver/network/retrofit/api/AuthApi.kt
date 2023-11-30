package com.ridehailing.driver.network.retrofit.api

import com.ridehailing.driver.models.api.LoginRequest
import com.ridehailing.driver.models.api.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auth/driver/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}