package com.cuongnl.ridehailing.network.socketio

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.screens.home.HomeActivity
import org.json.JSONObject

object BookingSocket {

    private const val EVENT_FIND_A_DRIVER = "find-a-driver"
    private const val EVENT_USER_CONNECT_SOCKET = "user-connect-socket"
    private const val EVENT_USER_CANCEL_TRIP = "user-cancel-trip"
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

    fun emitUserConnectSocket() {
        val json = JSONObject()
        json.put("phoneNumber", CurrentUser.getUser()?.phoneNumber?.value)

        socket?.emit(EVENT_USER_CONNECT_SOCKET, json)
    }

    fun emitFindADriver(data: JSONObject) {
        socket?.emit(EVENT_FIND_A_DRIVER, data)
    }

    fun emitUserCancelTrip(tripId: String) {
        val data = JSONObject()
        data.put("id", tripId)
        socket?.emit(EVENT_USER_CANCEL_TRIP, data)
    }
}