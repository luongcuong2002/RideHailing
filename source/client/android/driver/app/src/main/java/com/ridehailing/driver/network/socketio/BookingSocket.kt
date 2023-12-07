package com.ridehailing.driver.network.socketio

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ridehailing.driver.R
import com.ridehailing.driver.extensions.findActivity
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.screens.home.HomeActivity
import org.json.JSONObject

object BookingSocket {

    private const val EVENT_DRIVER_CONNECT_TO_SOCKET = "driver-connect-socket"
    private const val EVENT_UPDATE_DRIVER_LOCATION = "update-driver-location"
    private const val EVENT_DRIVER_CANCEL_TRIP = "driver-cancel-trip"
    private const val EVENT_NOTIFY_CANCEL_TRIP = "notify-cancel-trip"

    private var isConnected = false

    val socket = SocketClient.getSocket("/booking")

    fun connect(context: Context) {
        if (!isConnected) {
            socket?.let {
                it.connect()
                isConnected = true
                addCommonListeners(context)
            }
        }
    }

    fun disconnect() {
        socket?.disconnect()
        isConnected = false
        removeCommonListeners()
    }

    private fun addCommonListeners(context: Context) {
        socket?.on(EVENT_NOTIFY_CANCEL_TRIP) {
            val response = JSONObject(it[0].toString())
            val isSuccessful = response.getBoolean("success")

            if (isSuccessful) {
                val intent = Intent(context, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(intent)
                context.findActivity()?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, context.getString(R.string.the_trip_has_been_canceled), Toast.LENGTH_SHORT).show()
                }
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Notify cancel trip is not successful", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun removeCommonListeners() {
        socket?.off(EVENT_NOTIFY_CANCEL_TRIP)
    }

    fun emitDriverConnectToSocket(data: JSONObject) {
        socket?.emit(EVENT_DRIVER_CONNECT_TO_SOCKET, data)
    }

    fun emitToUpdateDriverLocation() {

        val data = JSONObject()

        data.put("currentLatitude", CurrentLocation.latLng.value.latitude)
        data.put("currentLongitude", CurrentLocation.latLng.value.longitude)

        socket?.emit(EVENT_UPDATE_DRIVER_LOCATION, data)
    }

    fun emitDriverCancelTrip(tripId: String) {
        val data = JSONObject()
        data.put("id", tripId)
        socket?.emit(EVENT_DRIVER_CANCEL_TRIP, data)
    }
}