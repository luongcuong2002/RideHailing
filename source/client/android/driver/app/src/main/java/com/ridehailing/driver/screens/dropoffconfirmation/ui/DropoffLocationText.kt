package com.ridehailing.driver.screens.dropoffconfirmation.ui

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ridehailing.driver.R
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DropoffLocationText() {
    AppText(
        text = stringResource(id = R.string.dropoff_customer_location),
        fontSize = 12.ssp,
        color = Color.White,
        modifier = Modifier
            .statusBarsPadding()
    )
}