package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.PaymentMethod
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.network.socketio.SocketClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class FindingDriverViewModel : ViewModel() {

    private companion object {
        private const val TAG = "BookingActivitySocketViewModel"
        private const val NAMESPACE = "/booking"
        private const val EVENT_REQUEST_A_RIDE = "request-a-ride"
    }

    private var _dotCount: MutableState<Int> = mutableStateOf(0)
    val dotCount: State<Int> = _dotCount

    private val mSocket = SocketClient.getSocket(NAMESPACE)

    private lateinit var pickupLatLng: LatLng
    private lateinit var transportationType: TransportationType
    private lateinit var paymentMethod: PaymentMethod
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
        mSocket?.on("response-a-ride") {

        }
    }

    fun emitRequestARide(data: JSONObject) {
        mSocket?.emit(EVENT_REQUEST_A_RIDE, data)
    }

    fun connect() {
        mSocket?.connect()
    }

    fun disconnect() {
        mSocket?.disconnect()
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
}