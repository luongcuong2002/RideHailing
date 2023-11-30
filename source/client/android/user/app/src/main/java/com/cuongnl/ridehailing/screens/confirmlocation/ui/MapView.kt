package com.cuongnl.ridehailing.screens.confirmlocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.ConfirmLocationState
import com.cuongnl.ridehailing.extensions.bitmapDescriptorFromVector
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.ConfirmLocationViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraMoveStartedReason
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    confirmLocationViewModel: ConfirmLocationViewModel = viewModel(),
    mapViewModel: MapViewModel = viewModel(),
    loaderViewModel: LoaderViewModel = viewModel(),
) {

    val context = LocalContext.current

    if (!loaderViewModel.isLoading.value) {

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                confirmLocationViewModel.destinationLocationLatLng.value!!,
                17f
            )
        }

        LaunchedEffect(cameraPositionState.isMoving) {
            if (!cameraPositionState.isMoving && cameraPositionState.cameraMoveStartedReason == CameraMoveStartedReason.GESTURE) {
                val target = cameraPositionState.position.target

                when (confirmLocationViewModel.confirmLocationState.value) {
                    ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> confirmLocationViewModel.setDestinationLocationLatLngAndLoadAddress(
                        context,
                        target
                    )

                    ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> confirmLocationViewModel.setPickupLocationLatLngAndLoadAddress(
                        context,
                        target
                    )
                }
            }
        }

        LaunchedEffect(confirmLocationViewModel.confirmLocationState.value) {
            when (confirmLocationViewModel.confirmLocationState.value) {
                ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {
                    if (confirmLocationViewModel.destinationLocationLatLng.value != null) {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLng(
                                confirmLocationViewModel.destinationLocationLatLng.value!!
                            )
                        )
                    } else {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLng(CurrentLocation.getLatLng())
                        )
                    }
                }

                ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                    if (confirmLocationViewModel.pickupLocationLatLng.value != null) {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLng(
                                confirmLocationViewModel.pickupLocationLatLng.value!!
                            )
                        )
                    } else {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLng(CurrentLocation.getLatLng())
                        )
                    }
                }
            }
        }

        val markerSize = with(LocalDensity.current) { (Constant.MARKER_SIZE_IN_PIXEL / density).dp }
        val markerImageInCenter = when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> painterResource(id = R.drawable.icons_dropoffmarker)
            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> painterResource(id = R.drawable.icons_pickupmarker)
        }

        Box(Modifier.fillMaxSize()) {

            GoogleMap(
                modifier = Modifier.matchParentSize(),
                properties = mapViewModel.properties.value,
                uiSettings = mapViewModel.uiSettings.value,
                cameraPositionState = cameraPositionState,
            ) {
                when (confirmLocationViewModel.confirmLocationState.value) {
                    ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {
                        if (confirmLocationViewModel.pickupLocationLatLng.value != null) {
                            Marker(
                                icon = context.bitmapDescriptorFromVector(R.drawable.icons_pickupmarker, Constant.MARKER_SIZE_IN_PIXEL),
                                state = MarkerState(position = confirmLocationViewModel.pickupLocationLatLng.value!!)
                            )
                        }
                    }
                    ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                        if (confirmLocationViewModel.destinationLocationLatLng.value != null) {
                            Marker(
                                icon = context.bitmapDescriptorFromVector(R.drawable.icons_dropoffmarker, Constant.MARKER_SIZE_IN_PIXEL),
                                state = MarkerState(position = confirmLocationViewModel.destinationLocationLatLng.value!!)
                            )
                        }
                    }
                }
            }

            Image(
                painter = markerImageInCenter,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = markerSize)
                    .align(Alignment.Center)
                    .size(markerSize)
            )
        }
    }
}