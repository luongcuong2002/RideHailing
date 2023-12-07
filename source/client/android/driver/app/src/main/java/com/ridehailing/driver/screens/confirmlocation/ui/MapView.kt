package com.ridehailing.driver.screens.confirmlocation.ui

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
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraMoveStartedReason
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.ridehailing.driver.R
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.utils.Constant
import com.ridehailing.driver.viewmodel.ConfirmLocationViewModel
import com.ridehailing.driver.viewmodel.MapViewModel

@Composable
fun MapView(
    confirmLocationViewModel: ConfirmLocationViewModel = viewModel(),
    mapViewModel: MapViewModel = viewModel(),
) {

    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            CurrentLocation.latLng.value,
            17f
        )
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving && cameraPositionState.cameraMoveStartedReason == CameraMoveStartedReason.GESTURE) {
            val target = cameraPositionState.position.target
            confirmLocationViewModel.setLocationLatLngAndLoadAddress(
                context,
                target
            )
        }
    }

    val markerSize = with(LocalDensity.current) { (Constant.MARKER_SIZE_IN_PIXEL / density).dp }
    val markerImageInCenter = painterResource(id = R.drawable.icons_dropoffmarker)

    Box(Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = mapViewModel.properties.value,
            uiSettings = mapViewModel.uiSettings.value,
            cameraPositionState = cameraPositionState,
        )

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