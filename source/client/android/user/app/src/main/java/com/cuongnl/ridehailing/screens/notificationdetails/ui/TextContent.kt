package com.cuongnl.ridehailing.screens.notificationdetails.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TextContent() {
    AppText(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl quis lacinia lacinia, nisl nisl la",
        fontSize = 14.sp,
        color = Color.Black,
        modifier = Modifier
            .padding(horizontal = 10.sdp)
    )
}