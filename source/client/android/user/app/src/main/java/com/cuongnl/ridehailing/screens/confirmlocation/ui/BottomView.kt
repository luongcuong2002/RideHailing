package com.cuongnl.ridehailing.screens.confirmlocation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BoxScope.BottomView() {
    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(topStart = 10.sdp, topEnd = 10.sdp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 10.sdp, start = 10.sdp, end = 10.sdp, top = 20.sdp)
            .pointerInput(Unit) {

            },
        verticalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.sdp))
                .background(colorResource(id = R.color.gray_200))
                .padding(10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            LocationIcon()
            AddressView()
            EditIcon()
        }
        ConfirmButton()
    }
}