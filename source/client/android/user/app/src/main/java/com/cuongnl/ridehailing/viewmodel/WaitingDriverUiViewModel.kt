package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.extensions.showDialog
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.cuongnl.ridehailing.screens.triptracking.TripTrackingActivity
import com.cuongnl.ridehailing.utils.Constant
import org.json.JSONObject

class WaitingDriverUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_NOTIFY_ARRIVED_AT_PICKUP = "notify-arrived-at-pickup"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _driverAcceptResponse: DriverAcceptResponse

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ARRIVED_AT_PICKUP) {
            val response = it[0].toString()
            val isSuccessful = JSONObject(response).getBoolean("success")

            if (isSuccessful) {
                navigationToTripTrackingActivity(context)
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Cannot notify arrived at pickup", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setDriverAcceptResponse(driverAcceptResponse: DriverAcceptResponse) {
        this._driverAcceptResponse = driverAcceptResponse
    }

    fun getDriverAcceptResponse(): DriverAcceptResponse {
        return _driverAcceptResponse
    }

    fun onClickTextingButton(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("smsto:${_driverAcceptResponse.driverInfo.phoneNumber}")
        context.startActivity(intent)
    }

    fun getVehicleIconId(): Int {
        return when(_driverAcceptResponse.driverInfo.travelMode) {
            TransportationType.TAXI.name -> TransportationType.TAXI.icon
            TransportationType.BIKE.name -> TransportationType.BIKE.icon
            else -> TransportationType.BIKE.icon
        }
    }

    fun onClickCallButton(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${_driverAcceptResponse.driverInfo.phoneNumber}")
        context.startActivity(intent)
    }

    fun clickBackButton(context: Context) {
        context.showDialog(
            title = context.getString(R.string.warning_text),
            message = context.getString(R.string.do_you_want_cancel_the_trip),
            textOfNegativeButton = context.getString(R.string.cancel_text),
            textOfPositiveButton = context.getString(R.string.ok_text),
            positiveButtonFunction = {
                BookingSocket.emitUserCancelTrip(_driverAcceptResponse.tripId)
            }
        )
    }

    private fun navigationToTripTrackingActivity(context: Context) {
        val intent = Intent(context, TripTrackingActivity::class.java)
        intent.putExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE, _driverAcceptResponse)
        context.findActivity()?.finish()
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}