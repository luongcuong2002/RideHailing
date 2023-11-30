package com.cuongnl.ridehailing.screens.findingdriver.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BoxScope.AppBar() {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(top = 15.dp)
            .padding(horizontal = 20.sdp)
            .fillMaxWidth()
            .align(Alignment.TopCenter),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton()
        Cost()
    }
}