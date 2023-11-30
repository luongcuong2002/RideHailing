package com.ridehailing.driver.screens.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ridehailing.driver.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.login_logo),
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 24.sdp, horizontal = 70.sdp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}