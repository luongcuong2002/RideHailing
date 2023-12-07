package com.ridehailing.driver.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.extensions.findActivity
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.network.socketio.BookingSocket
import org.json.JSONObject

class PaymentConfirmationUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_TRIP_COMPLETE = "trip-completed"
        private const val EVENT_NOTIFY_TRIP_COMPLETE = "notify-trip-completed"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _currentTripInfo : TripInfo

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_TRIP_COMPLETE) {

            val response = JSONObject(it[0].toString())

            val isSuccessful = response.getBoolean("success")

            if (isSuccessful) {
                context.findActivity()?.finish()
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Cannot notify trip complete", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun removeListeners() {
        mSocket?.off(EVENT_NOTIFY_TRIP_COMPLETE)
    }

    val currentTripInfo : TripInfo
        get() = _currentTripInfo

    fun setCurrentTripInfo(tripInfo : TripInfo) {
        _currentTripInfo = tripInfo
    }

    fun clickConfirmButton() {
        val data = JSONObject()
        data.put("id", currentTripInfo.id)
        mSocket?.emit(EVENT_TRIP_COMPLETE, data)
    }
}