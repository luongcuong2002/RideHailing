package com.cuongnl.ridehailing.screens.waitingdriver.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.bitmapDescriptorFromVector
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.cuongnl.ridehailing.viewmodel.WaitingDriverUiViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun ColumnScope.MapView(
    mapViewModel: MapViewModel = viewModel(),
    waitingDriverUiViewModel: WaitingDriverUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val pickupLocationLatLng = LatLng(
        waitingDriverUiViewModel.getDriverAcceptResponse().pickupLatitude,
        waitingDriverUiViewModel.getDriverAcceptResponse().pickupLongitude
    )

    val driverLocationLatLng = LatLng(
        waitingDriverUiViewModel.getDriverAcceptResponse().driverInfo.currentLatitude,
        waitingDriverUiViewModel.getDriverAcceptResponse().driverInfo.currentLongitude
    )

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            properties = mapViewModel.properties.value,
            uiSettings = mapViewModel.uiSettings.value,
            cameraPositionState = mapViewModel.cameraPositionState.value,
            contentPadding = PaddingValues(bottom = LocalConfiguration.current.screenHeightDp.dp)
        ) {

            Marker(
                icon = context.bitmapDescriptorFromVector(R.drawable.icons_dropoffmarker, Constant.MARKER_SIZE_IN_PIXEL),
                state = MarkerState(position = pickupLocationLatLng)
            )

            Marker(
                icon = context.bitmapDescriptorFromVector(waitingDriverUiViewModel.getVehicleIconId(), Constant.MARKER_SIZE_IN_PIXEL),
                state = MarkerState(position = driverLocationLatLng)
            )
        }
    }
}