package com.cuongnl.ridehailing.screens.findingdriver.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapView(
    mapViewModel: MapViewModel = viewModel(),
) {

    val markerSize = with(LocalDensity.current) { (Constant.MARKER_SIZE_IN_PIXEL / density).dp }

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
        )

        RadarScanner(
            modifier = Modifier
                .align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.icons_pickupmarker),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = markerSize)
                .align(Alignment.Center)
                .size(markerSize)
        )
    }
}