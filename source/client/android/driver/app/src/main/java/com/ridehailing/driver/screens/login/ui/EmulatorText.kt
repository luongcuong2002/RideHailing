package com.ridehailing.driver.screens.login.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.ssp
import com.ridehailing.driver.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun EmulatorText() {
    AppText(
        modifier = Modifier
            .padding(top = 40.sdp),
        text = stringResource(id = R.string.driver_emulator_text),
        fontSize = 20.ssp,
        color = Color.Black,
        fontWeight = FontWeight.Medium
    )
}