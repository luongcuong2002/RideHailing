package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.ridehailing.driver.R
import com.ridehailing.driver.extensions.findActivity
import com.ridehailing.driver.extensions.showDialog
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.screens.dropoffconfirmation.DropoffConfirmationActivity
import com.ridehailing.driver.screens.paymentconfirmation.PaymentConfirmationActivity
import com.ridehailing.driver.utils.Constant
import com.ridehailing.driver.utils.MapUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

class DropoffConfirmationUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_DRIVER_ARRIVED_AT_DESTINATION = "driver-arrived-at-destination"
        private const val EVENT_NOTIFY_ARRIVED_AT_DESTINATION = "notify-arrived-at-destination"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _currentTripInfo: TripInfo

    val currentTripInfo: TripInfo
        get() = _currentTripInfo

    private val _timeTravelInMilliseconds = mutableStateOf(0L)
    val timeTravelInMilliseconds: MutableState<Long> = _timeTravelInMilliseconds

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ARRIVED_AT_DESTINATION) {

            val response = JSONObject(it[0].toString())

            val isSuccessful = response.getBoolean("success")

            if (isSuccessful) {
                navigationToPaymentConfirmationActivity(context)
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Cannot notify arrived at destination", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun removeListeners() {
        mSocket?.off(EVENT_NOTIFY_ARRIVED_AT_DESTINATION)
    }

    fun setCurrentTripInfo(tripInfo: TripInfo) {
        _currentTripInfo = tripInfo
    }

    fun onClickDirectionButton(context: Context) {

        val uriString =
            "http://maps.google.com/maps?daddr=${currentTripInfo.destinationAddress.latitude},${currentTripInfo.destinationAddress.longitude}"

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

    fun navigationToPaymentConfirmationActivity(context: Context) {
        val intent = Intent(context, PaymentConfirmationActivity::class.java)
        intent.putExtra(Constant.BUNDLE_TRIP_INFO, currentTripInfo)
        context.startActivity(intent)
        context.findActivity()?.finish()
        context.findActivity()?.overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }

    fun onSwipeToConfirm() {
        val data = JSONObject()
        data.put("id", currentTripInfo.id)

        mSocket?.emit(EVENT_DRIVER_ARRIVED_AT_DESTINATION, data)
    }

    fun driverMoving() {
        _timeTravelInMilliseconds.value = 10000L
        val stepNumber = 20

        val delayTime = timeTravelInMilliseconds.value / stepNumber

        val steps = MapUtils.generateIntermediateLatLngs(
            LatLng(CurrentLocation.latLng.value.latitude, CurrentLocation.latLng.value.longitude),
            LatLng(currentTripInfo.destinationAddress.latitude, currentTripInfo.destinationAddress.longitude),
            stepNumber
        )

        CoroutineScope(Dispatchers.Main).launch {
            steps.forEach {

                val data = JSONObject()
                data.put("userSocketId", currentTripInfo.userSocketId)

                CurrentLocation.setLatLngAndUpdateToServer(it, data)
                delay(delayTime)
                _timeTravelInMilliseconds.value -= delayTime
            }
            _timeTravelInMilliseconds.value = 0L
        }
    }
}