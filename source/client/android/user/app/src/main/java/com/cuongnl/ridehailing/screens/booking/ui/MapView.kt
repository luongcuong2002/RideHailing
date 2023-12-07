package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.bitmapDescriptorFromVector
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline

@Composable
fun MapView(
    mapViewModel: MapViewModel = viewModel(),
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val context = LocalContext.current

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = mapViewModel.properties.value,
        uiSettings = mapViewModel.uiSettings.value,
        cameraPositionState = mapViewModel.cameraPositionState.value,
    ) {
        Marker(
            icon = context.bitmapDescriptorFromVector(R.drawable.icons_pickupmarker, Constant.MARKER_SIZE_IN_PIXEL),
            state = MarkerState(position = bookingActivityUiViewModel.pickupLocationLatLng.value)
        )
        Marker(
            icon = context.bitmapDescriptorFromVector(R.drawable.icons_dropoffmarker, Constant.MARKER_SIZE_IN_PIXEL),
            state = MarkerState(position = bookingActivityUiViewModel.destinationLocationLatLng.value)
        )

        if (bookingActivityUiViewModel.bookingsInfo[bookingActivityUiViewModel.selectedBookingIndex.value].directionPoints != null) {
            Polyline(
                points = bookingActivityUiViewModel.bookingsInfo[bookingActivityUiViewModel.selectedBookingIndex.value].directionPoints!!,
                color = colorResource(id = R.color.map_route_primary_color),
                width = 12f,
                jointType = JointType.ROUND,
            )
        }

        bookingActivityUiViewModel.getCurrentBookingInfo().bookingInfoResponse?.let {
            it.driversNearbyLocation.forEach { latLng ->
                Marker(
                    icon = context.bitmapDescriptorFromVector(bookingActivityUiViewModel.getCurrentBookingInfo().transportationType.icon, Constant.MARKER_SIZE_IN_PIXEL),
                    state = MarkerState(position = LatLng(latLng.latitude, latLng.longitude))
                )
            }
        }

        MarkerComposable(
            state = MarkerState(position = bookingActivityUiViewModel.pickupLocationLatLng.value)
        ) {
            PickupLocationMarker()
        }

        MarkerComposable(
            state = MarkerState(position = bookingActivityUiViewModel.destinationLocationLatLng.value)
        ) {
            DestinationLocationMarker()
        }
    }

}