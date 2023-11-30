package com.cuongnl.ridehailing.screens.notificationdetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.widgets.NoRippleButton
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.sdp)
            .fillMaxWidth()
            .height(Constant.APP_BAR_HEIGHT.sdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton()
        DeleteButton()
    }
}

@Composable
private fun BackButton() {

    NoRippleButton(
        onClick = {

        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp),
        )
    }
}

@Composable
private fun DeleteButton() {
    TouchableOpacityButton(
        onClick = {

        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_trash),
            contentDescription = null,
            modifier = Modifier
                .size(15.sdp)
        )
    }
}