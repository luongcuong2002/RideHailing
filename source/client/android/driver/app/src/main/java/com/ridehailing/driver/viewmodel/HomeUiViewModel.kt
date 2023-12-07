package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.screens.confirmlocation.ConfirmLocationActivity
import com.ridehailing.driver.screens.pickupconfirmation.PickupConfirmationActivity
import com.ridehailing.driver.utils.Constant
import org.json.JSONObject

class HomeUiViewModel : ViewModel() {

    private companion object {
        const val EVENT_SEND_REQUESTING_A_RIDE = "send-requesting-a-ride-to-drivers"
        const val EVENT_DELETE_TRIP_RECORD = "notify-delete-trip-record"
        const val EVENT_NOTIFY_ACCEPT_REQUEST = "notify-accept-request"
        const val EVENT_DRIVER_ACCEPT_REQUEST = "driver-accept-request"
    }

    private val trips = mutableStateListOf<TripInfo>()

    private var _selectedTrip = mutableStateOf<TripInfo?>(null)

    val selectedTrip: State<TripInfo?> = _selectedTrip

    private val mSocket = BookingSocket.socket

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_SEND_REQUESTING_A_RIDE) {
            val json = JSONObject(it[0].toString())
            val trip = TripInfo.fromJson(json)
            addTrip(trip)
        }
        mSocket?.on(EVENT_DELETE_TRIP_RECORD) {
            val json = JSONObject(it[0].toString())
            val id = json.getString("id")
            removeTripById(id)
        }
        mSocket?.on(EVENT_NOTIFY_ACCEPT_REQUEST) {
            val json = JSONObject(it[0].toString())
            val tripId = json.getString("id")
            val isSuccessful = json.getBoolean("success")
            if (isSuccessful) {
                navigateToPickupConfirmationActivity(context)
            }
            removeCurrentTripById(tripId)
        }
    }

    fun getTrips(): List<TripInfo> {
        return trips
    }

    fun addTrip(trip: TripInfo) {
        trips.add(0, trip)
    }

    fun removeTripById(tripId: String) {
        trips.removeIf { trip -> trip.id == tripId }
    }

    fun removeCurrentTripById(tripId: String) {
        removeTripById(tripId)
        setSelectedTrip(null)
    }

    fun setSelectedTrip(trip: TripInfo?) {
        _selectedTrip.value = trip
    }

    fun clickToAcceptARide() {
        val data = JSONObject()
        data.put("id", _selectedTrip.value?.id)

        mSocket?.emit(EVENT_DRIVER_ACCEPT_REQUEST, data)
    }

    fun navigateToPickupConfirmationActivity(context: Context) {
        val intent = Intent(context, PickupConfirmationActivity::class.java)
        intent.putExtra(Constant.BUNDLE_TRIP_INFO, _selectedTrip.value)
        context.startActivity(intent)
    }

    fun clickChangeLocation(context: Context) {
        val intent = Intent(context, ConfirmLocationActivity::class.java)
        context.startActivity(intent)
    }
}