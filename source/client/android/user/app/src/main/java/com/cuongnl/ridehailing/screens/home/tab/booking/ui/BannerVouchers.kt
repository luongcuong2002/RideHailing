package com.cuongnl.ridehailing.screens.home.tab.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BannerVouchers() {
    Image(
        painter = painterResource(id = R.drawable.banner_voucher_1),
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 5.sdp)
            .clip(RoundedCornerShape(10))
            .fillMaxWidth()
            .aspectRatio(2.9f),
        contentScale = ContentScale.Crop,
    )
}