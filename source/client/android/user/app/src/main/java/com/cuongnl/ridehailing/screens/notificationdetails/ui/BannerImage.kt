package com.cuongnl.ridehailing.screens.notificationdetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BannerImage() {
    Image(
        painter = painterResource(id = R.drawable.notification_banner_for_testing),
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 10.sdp)
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}