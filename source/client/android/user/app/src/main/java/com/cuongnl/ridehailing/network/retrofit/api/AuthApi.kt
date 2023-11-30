package com.cuongnl.ridehailing.network.retrofit.api

import com.cuongnl.ridehailing.models.api.LoginRequest
import com.cuongnl.ridehailing.models.api.LoginResponse
import com.cuongnl.ridehailing.models.api.RegisterRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @GET("auth/user/check-existing-user")
    fun checkExistingUser(@Query("phoneNumber") phoneNumber: String): Call<ScalarsBooleanResponse>

    @POST("auth/user/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/user/register")
    fun register(@Body request: RegisterRequest): Call<ScalarsBooleanResponse>
}