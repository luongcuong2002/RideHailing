package com.ridehailing.driver.screens.home.tab.info.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import com.ridehailing.driver.R
import com.ridehailing.driver.extensions.toBitmap
import com.ridehailing.driver.globalstate.CurrentDriver
import ir.kaaveh.sdpcompose.sdp

@Composable
fun Avatar() {

    val avatar = CurrentDriver.getDriver()?.driverAvatar?.toBitmap()?.asImageBitmap()

    val modifier = Modifier
        .padding(vertical = 24.sdp)
        .size(110.sdp)
        .clip(CircleShape)
        .border(
            width = 2.sdp,
            color = androidx.compose.ui.graphics.Color(0xFF1E88E5),
            shape = CircleShape
        )

    if (avatar == null) {
        Image(
            contentDescription = null,
            painter = painterResource(id = R.drawable.default_avatar),
            modifier = modifier
        )
    } else {
        Image(
            contentDescription = null,
            bitmap = avatar,
            modifier = modifier
        )
    }
}