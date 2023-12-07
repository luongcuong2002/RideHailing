package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.models.api.RatingDriverRequest
import com.cuongnl.ridehailing.network.retrofit.repository.BookingRepository
import com.cuongnl.ridehailing.screens.home.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripDetailsUiViewModel : ViewModel() {

    private val bookingRepository = BookingRepository()

    private lateinit var _driverAcceptResponse: DriverAcceptResponse
    private var cost = 0

    private val _starNumber = mutableStateOf(0)
    private val _isSendingRating = mutableStateOf(false)
    private val _isRated = mutableStateOf(false)

    val starNumber: State<Int> = _starNumber
    val isSendingRating: State<Boolean> = _isSendingRating
    val isRated: State<Boolean> = _isRated

    fun onClickBackButton(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun onRatingDriver(context: Context) {

        val request = RatingDriverRequest(
            driverId = _driverAcceptResponse.driverInfo.id,
            star = starNumber.value
        )

        setSendingRating(true)

        bookingRepository.ratingDriver(request, object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.isSuccessful) {
                    _isRated.value = true
                    Toast.makeText(context, context.getString(R.string.rating_driver_successfully), Toast.LENGTH_SHORT).show()
                    setSendingRating(false)
                } else {
                    Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
                    setSendingRating(false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
                setSendingRating(false)
            }
        })
    }

    fun setStarNumber(number: Int) {
        _starNumber.value = number
    }

    fun setSendingRating(isSending: Boolean) {
        _isSendingRating.value = isSending
    }

    fun getDriverAcceptResponse(): DriverAcceptResponse {
        return _driverAcceptResponse
    }

    fun setDriverAcceptResponse(driverAcceptResponse: DriverAcceptResponse) {
        this._driverAcceptResponse = driverAcceptResponse
    }

    fun setCost(value: Int) {
        cost = value
    }

    fun getCost(): Int {
        return cost
    }
}