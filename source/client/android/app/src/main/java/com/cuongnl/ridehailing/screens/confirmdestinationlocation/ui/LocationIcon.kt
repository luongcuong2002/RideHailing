package com.cuongnl.ridehailing.screens.confirmdestinationlocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun LocationIcon() {
    Image(
        painter = painterResource(id = R.drawable.iconplacecircle),
        contentDescription = null,
        modifier = Modifier
            .size(25.sdp)
    )
}