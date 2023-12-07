package com.cuongnl.ridehailing.screens.triptracking

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.screens.triptracking.ui.BottomView
import com.cuongnl.ridehailing.screens.triptracking.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.cuongnl.ridehailing.viewmodel.TripTrackingUiViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class TripTrackingActivity : BaseActivity() {

    private lateinit var tripTrackingUiViewModel: TripTrackingUiViewModel
    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModels()

        setContent {
            Screen()
        }
    }

    private fun setupViewModels() {
        tripTrackingUiViewModel = ViewModelProvider(this)[TripTrackingUiViewModel::class.java]

        val driverAcceptResponse = intent.getSerializableExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE) as DriverAcceptResponse

        tripTrackingUiViewModel.setupListeners(this)
        tripTrackingUiViewModel.setDriverAcceptResponseAndUpdateDriverLocation(driverAcceptResponse)

        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]

        val destinationLatLng = LatLng(
            tripTrackingUiViewModel.getDriverAcceptResponse().destinationLatitude,
            tripTrackingUiViewModel.getDriverAcceptResponse().destinationLongitude
        )

        val pickupLatLng = LatLng(
            tripTrackingUiViewModel.getDriverAcceptResponse().pickupLatitude,
            tripTrackingUiViewModel.getDriverAcceptResponse().pickupLongitude
        )

        val zoomLevelFitMap = MapUtils.getZoomLevelFitMap(destinationLatLng, pickupLatLng)
        mapViewModel.setCameraPositionState(
            CameraPositionState(
                position = CameraPosition.fromLatLngZoom(
                    LatLng(
                        (pickupLatLng.latitude + destinationLatLng.latitude) / 2,
                        (pickupLatLng.longitude + destinationLatLng.longitude) / 2,
                    ),
                    zoomLevelFitMap
                )
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        tripTrackingUiViewModel.removeListeners()
    }

    override fun onBackPressed() {

    }
}

@Composable
private fun Screen() {
    AppTheme {
        MapView()
        BottomView()
    }
}