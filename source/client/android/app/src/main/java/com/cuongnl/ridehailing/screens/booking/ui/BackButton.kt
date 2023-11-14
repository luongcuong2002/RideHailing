package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BoxScope.BackButton() {

    TouchableOpacityButton(
        onClick = {

        },
        modifier = Modifier
            .padding(top = 20.sdp, start = 20.sdp)
            .statusBarsPadding()
            .align(Alignment.TopStart)
            .shadow(
                10.dp,
                RoundedCornerShape(100)
            )
            .size(28.sdp)
            .clip(RoundedCornerShape(100))
            .shadow(5.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .padding(7.dp)
                .padding(end = 2.dp)
                .align(Alignment.Center)
                .fillMaxSize()
        )
    }
}