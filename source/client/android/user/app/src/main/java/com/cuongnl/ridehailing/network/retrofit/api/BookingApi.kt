package com.cuongnl.ridehailing.network.retrofit.api

import com.cuongnl.ridehailing.models.api.GetBookingInfoRequest
import com.cuongnl.ridehailing.models.api.GetBookingInfoResponse
import com.cuongnl.ridehailing.models.api.RatingDriverRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BookingApi {
    @POST("booking/get-booking-info")
    fun getBookingInfo(@Body request: GetBookingInfoRequest): Call<GetBookingInfoResponse>

    @POST("booking/rating-driver")
    fun ratingDriver(@Body request: RatingDriverRequest): Call<Void>
}