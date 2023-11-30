package com.ridehailing.driver.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.extensions.findActivity
import com.ridehailing.driver.models.TripInfo

class PaymentConfirmationUiViewModel : ViewModel() {

    private lateinit var _currentTripInfo : TripInfo

    val currentTripInfo : TripInfo
        get() = _currentTripInfo

    fun setCurrentTripInfo(tripInfo : TripInfo) {
        _currentTripInfo = tripInfo
    }

    fun clickConfirmButton(context: Context) {
        context.findActivity()?.finish()
    }
}