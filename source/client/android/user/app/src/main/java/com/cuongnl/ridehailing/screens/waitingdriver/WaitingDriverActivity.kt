package com.cuongnl.ridehailing.screens.waitingdriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.screens.waitingdriver.ui.BackButton
import com.cuongnl.ridehailing.screens.waitingdriver.ui.BottomView
import com.cuongnl.ridehailing.screens.waitingdriver.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.cuongnl.ridehailing.viewmodel.WaitingDriverUiViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class WaitingDriverActivity : BaseActivity() {

    private lateinit var waitingDriverUiViewModel: WaitingDriverUiViewModel
    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        waitingDriverUiViewModel = ViewModelProvider(this)[WaitingDriverUiViewModel::class.java]

        val driverAcceptResponse = intent.getSerializableExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE) as DriverAcceptResponse

        waitingDriverUiViewModel.setupListeners(this)
        waitingDriverUiViewModel.setDriverAcceptResponse(driverAcceptResponse)

        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]

        val pickupLocationLatLng = LatLng(
            waitingDriverUiViewModel.getDriverAcceptResponse().pickupLatitude,
            waitingDriverUiViewModel.getDriverAcceptResponse().pickupLongitude
        )

        val driverLocationLatLng = LatLng(
            waitingDriverUiViewModel.getDriverAcceptResponse().driverInfo.currentLatitude,
            waitingDriverUiViewModel.getDriverAcceptResponse().driverInfo.currentLongitude
        )

        val zoomLevelFitMap = MapUtils.getZoomLevelFitMap(driverLocationLatLng, pickupLocationLatLng)
        val pickupZoomLevel = zoomLevelFitMap - 1f
        mapViewModel.setCameraPositionState(
            CameraPositionState(
                position = CameraPosition.fromLatLngZoom(
                    LatLng(
                        (driverLocationLatLng.latitude + pickupLocationLatLng.latitude) / 2,
                        (driverLocationLatLng.longitude + pickupLocationLatLng.longitude) / 2,
                    ),
                    pickupZoomLevel
                )
            )
        )
    }

    override fun onBackPressed() {
        waitingDriverUiViewModel.clickBackButton(this)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                MapView()
                BottomView()
            }
            BackButton()
        }
    }
}