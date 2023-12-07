package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.extensions.findActivity
import com.ridehailing.driver.extensions.showDialog
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.screens.dropoffconfirmation.DropoffConfirmationActivity
import com.ridehailing.driver.utils.Constant
import org.json.JSONObject

class PickupConfirmationUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_DRIVER_ARRIVED_AT_PICKUP = "driver-arrived-at-pickup"
        private const val EVENT_NOTIFY_ARRIVED_AT_PICKUP = "notify-arrived-at-pickup"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _currentTripInfo : TripInfo

    val currentTripInfo : TripInfo
        get() = _currentTripInfo

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ARRIVED_AT_PICKUP) {

            val response = JSONObject(it[0].toString())

            val isSuccessful = response.getBoolean("success")

            if (isSuccessful) {
                val intent = Intent(context, DropoffConfirmationActivity::class.java)
                intent.putExtra(Constant.BUNDLE_TRIP_INFO, currentTripInfo)
                context.startActivity(intent)
                context.findActivity()?.finish()
                context.findActivity()?.overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Cannot notify arrived at pickup", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun removeListeners() {
        mSocket?.off(EVENT_NOTIFY_ARRIVED_AT_PICKUP)
    }

    fun setCurrentTripInfo(tripInfo : TripInfo) {
        _currentTripInfo = tripInfo
    }

    fun onClickDirectionButton(context: Context) {

        val uriString = "http://maps.google.com/maps?daddr=${currentTripInfo.pickupAddress.latitude},${currentTripInfo.pickupAddress.longitude}"

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(uriString)
        )
        context.startActivity(intent)
    }

    fun onClickTextingButton(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("smsto:${currentTripInfo.userInfo.phoneNumber}")
        context.startActivity(intent)
    }

    fun onClickCallButton(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${currentTripInfo.userInfo.phoneNumber}")
        context.startActivity(intent)
    }

    fun onClickCancelTripButton(context: Context) {
        context.showDialog(
            title = context.getString(R.string.warning_text),
            message = context.getString(R.string.do_you_want_cancel_the_trip),
            textOfNegativeButton = context.getString(R.string.cancel_text),
            textOfPositiveButton = context.getString(R.string.ok_text),
            positiveButtonFunction = {
                BookingSocket.emitDriverCancelTrip(currentTripInfo.id)
            }
        )
    }

    fun onSwipeToConfirm() {
        val data = JSONObject()
        data.put("id", currentTripInfo.id)

        mSocket?.emit(EVENT_DRIVER_ARRIVED_AT_PICKUP, data)
    }
}