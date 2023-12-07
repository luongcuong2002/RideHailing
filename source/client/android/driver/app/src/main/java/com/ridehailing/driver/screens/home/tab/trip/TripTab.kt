package com.ridehailing.driver.screens.home.tab.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ridehailing.driver.screens.home.tab.trip.ui.AppBar
import com.ridehailing.driver.screens.home.tab.trip.ui.ConfirmDialog
import com.ridehailing.driver.screens.home.tab.trip.ui.TripList

@Composable
fun TripTab() {
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