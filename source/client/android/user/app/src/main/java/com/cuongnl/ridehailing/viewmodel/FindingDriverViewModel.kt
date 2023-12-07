package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.PaymentMethod
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.cuongnl.ridehailing.screens.waitingdriver.WaitingDriverActivity
import com.cuongnl.ridehailing.utils.Constant
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class FindingDriverViewModel : ViewModel() {

    private companion object {
        private const val EVENT_NOTIFY_ACCEPT_REQUEST = "notify-accept-request"
    }

    private var _dotCount: MutableState<Int> = mutableStateOf(0)
    val dotCount: State<Int> = _dotCount

    private val mSocket = BookingSocket.socket

    private lateinit var pickupLatLng: LatLng
    private lateinit var destinationLatLng: LatLng
    private lateinit var transportationType: TransportationType
    private lateinit var paymentMethod: PaymentMethod
    private var minutesToDriverArrival: Int = 1
    private var cost: Int = 0

    init {
        runDotAnimation()
    }

    private fun runDotAnimation() {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                setDotCount((dotCount.value + 1) % 3)
                withContext(Dispatchers.IO) {
                    Thread.sleep(500)
                }
            }
        }
    }

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ACCEPT_REQUEST) {
            val response = JSONObject(it[0].toString())

            val driverAcceptResponse = DriverAcceptResponse.fromJson(response)

            val intent = Intent(context, WaitingDriverActivity::class.java)
            intent.putExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE, driverAcceptResponse)

            navigateToWaitingDriverActivity(context, intent)
        }
    }

    private fun navigateToWaitingDriverActivity(context: Context, intent: Intent) {
        context.startActivity(intent)
    }

    fun offListeners() {
        mSocket?.off(EVENT_NOTIFY_ACCEPT_REQUEST)
    }

    fun setDotCount(dotCount: Int) {
        _dotCount.value = dotCount
    }

    fun getPickupLatLng(): LatLng {
        return pickupLatLng
    }

    fun setPickupLatLng(pickupLatLng: LatLng) {
        this.pickupLatLng = pickupLatLng
    }

    fun getTransportationType(): TransportationType {
        return transportationType
    }

    fun setTransportationType(transportationType: TransportationType) {
        this.transportationType = transportationType
    }

    fun getPaymentMethod(): PaymentMethod {
        return paymentMethod
    }

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        this.paymentMethod = paymentMethod
    }

    fun getCost(): Int {
        return cost
    }

    fun setCost(cost: Int) {
        this.cost = cost
    }

    fun getMinutesToDriverArrival(): Int {
        return minutesToDriverArrival
    }

    fun setMinutesToDriverArrival(minutesToDriverArrival: Int) {
        this.minutesToDriverArrival = minutesToDriverArrival
    }

    fun getDestinationLatLng(): LatLng {
        return destinationLatLng
    }

    fun setDestinationLatLng(destinationLatLng: LatLng) {
        this.destinationLatLng = destinationLatLng
    }
}