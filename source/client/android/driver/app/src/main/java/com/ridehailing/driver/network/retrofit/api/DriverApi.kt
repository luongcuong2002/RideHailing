package com.ridehailing.driver.network.retrofit.api

import com.ridehailing.driver.models.api.FetchDriverResponse
import retrofit2.Call
import retrofit2.http.GET

interface DriverApi {

    @GET("driver/current")
    fun fetchDriver(): Call<FetchDriverResponse>
}