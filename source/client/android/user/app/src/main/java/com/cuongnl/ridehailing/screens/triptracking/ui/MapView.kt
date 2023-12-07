package com.cuongnl.ridehailing.screens.triptracking.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.cuongnl.ridehailing.viewmodel.TripTrackingUiViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.android.gms.maps.model.LatLng
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.bitmapDescriptorFromVector

@Composable
fun MapView(
    mapViewModel: MapViewModel = viewModel(),
    tripTrackingUiViewModel: TripTrackingUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val destinationLatLng = LatLng(
        tripTrackingUiViewModel.getDriverAcceptResponse().destinationLatitude,
        tripTrackingUiViewModel.getDriverAcceptResponse().destinationLongitude
    )

    val pickupLatLng = LatLng(
        tripTrackingUiViewModel.getDriverAcceptResponse().pickupLatitude,
        tripTrackingUiViewModel.getDriverAcceptResponse().pickupLongitude
    )

    Box(
        modifier = Modifier
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
                state = MarkerState(
                    position = destinationLatLng
                )
            )
            Marker(
                icon = context.bitmapDescriptorFromVector(R.drawable.icons_pickupmarker, Constant.MARKER_SIZE_IN_PIXEL),
                state = MarkerState(
                    position = pickupLatLng
                )
            )
            Marker(
                icon = context.bitmapDescriptorFromVector(tripTrackingUiViewModel.getVehicleIconId(), Constant.MARKER_SIZE_IN_PIXEL),
                state = MarkerState(
                    position = tripTrackingUiViewModel.driverLocation.value
                )
            )
        }
    }
}