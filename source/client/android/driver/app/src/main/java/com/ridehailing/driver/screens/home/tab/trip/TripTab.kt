package com.ridehailing.driver.screens.home.tab.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.screens.home.tab.trip.ui.AppBar
import com.ridehailing.driver.screens.home.tab.trip.ui.ConfirmDialog
import com.ridehailing.driver.screens.home.tab.trip.ui.TripList
import com.ridehailing.driver.viewmodel.TripTabUiViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TripTab(
    tripTabUiViewModel: TripTabUiViewModel = viewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        tripTabUiViewModel.setupListeners(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBar()
        TripList()
        ConfirmDialog()
    }
}