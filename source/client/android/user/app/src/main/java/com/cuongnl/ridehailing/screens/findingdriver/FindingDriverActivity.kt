package com.cuongnl.ridehailing.screens.findingdriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.RequestARideRequest
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.cuongnl.ridehailing.screens.findingdriver.ui.AppBar
import com.cuongnl.ridehailing.screens.findingdriver.ui.BottomView
import com.cuongnl.ridehailing.screens.findingdriver.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.FindingDriverViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class FindingDriverActivity : BaseActivity() {

    private lateinit var findingDriverViewModel: FindingDriverViewModel
    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        connectBookingSocket()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        findingDriverViewModel = ViewModelProvider(this)[FindingDriverViewModel::class.java]

        findingDriverViewModel.setupListeners(this)

        val requestData = intent.getSerializableExtra(Constant.BUNDLE_REQUEST_A_RIDE_REQUEST) as RequestARideRequest

        val pickupLatLng = LatLng(requestData.pickupLatitude, requestData.pickupLongitude)
        val destinationLatLng = LatLng(requestData.destinationLatitude, requestData.destinationLongitude)

        findingDriverViewModel.setPickupLatLng(pickupLatLng)
        findingDriverViewModel.setTransportationType(requestData.transportationType)
        findingDriverViewModel.setPaymentMethod(requestData.paymentMethod)
        findingDriverViewModel.setCost(requestData.cost)
        findingDriverViewModel.setDestinationLatLng(destinationLatLng)
        findingDriverViewModel.setMinutesToDriverArrival(requestData.minutesToDriverArrival)

        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]
        mapViewModel.setCameraPositionState(
            CameraPositionState(
                position = CameraPosition.fromLatLngZoom(pickupLatLng, 15f)
            )
        )
    }

    private fun connectBookingSocket() {

        val requestData = intent.getSerializableExtra(Constant.BUNDLE_REQUEST_A_RIDE_REQUEST) as RequestARideRequest

        BookingSocket.connect(this)
        BookingSocket.emitUserConnectSocket()
        BookingSocket.emitFindADriver(requestData.toJson())
    }

    override fun onDestroy() {
        super.onDestroy()
        findingDriverViewModel.offListeners()
        BookingSocket.disconnect()
    }
}

@Composable
private fun Screen() {
    AppTheme {
        MapView()
        AppBar()
        BottomView()
    }
}