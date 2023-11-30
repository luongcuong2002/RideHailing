package com.cuongnl.ridehailing.network.retrofit.repository

import com.cuongnl.ridehailing.models.api.GetBookingInfoRequest
import com.cuongnl.ridehailing.models.api.GetBookingInfoResponse
import com.cuongnl.ridehailing.network.retrofit.RetrofitClient
import com.cuongnl.ridehailing.network.retrofit.api.BookingApi
import retrofit2.Callback

class BookingRepository {

    private val bookingApi: BookingApi = RetrofitClient.getClient().create(BookingApi::class.java)

    fun getBookingInfo(bookingInfoRequest: GetBookingInfoRequest, callback: Callback<GetBookingInfoResponse>) {
        bookingApi.getBookingInfo(bookingInfoRequest).enqueue(callback)
    }
}