package com.cuongnl.ridehailing.screens.permission.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BoxScope.BannerImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 75.sdp, start = 50.sdp, end = 50.sdp)
            .align(Alignment.TopCenter),
        painter = painterResource(R.drawable.banner_req_permis),
        contentDescription = null
    )
}