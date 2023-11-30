package com.ridehailing.driver.network.retrofit.repository

import com.ridehailing.driver.models.api.FetchDriverResponse
import com.ridehailing.driver.network.retrofit.RetrofitClient
import com.ridehailing.driver.network.retrofit.api.DriverApi
import retrofit2.Callback

class DriverRepository {

    private val driverApi: DriverApi = RetrofitClient.getClient().create(DriverApi::class.java)

    fun fetchDriver(callback: Callback<FetchDriverResponse>) {
        driverApi.fetchDriver().enqueue(callback)
    }
}