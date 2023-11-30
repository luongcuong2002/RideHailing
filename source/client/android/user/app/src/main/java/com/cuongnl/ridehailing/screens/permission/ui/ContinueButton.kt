package com.cuongnl.ridehailing.screens.permission.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.permission.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ContinueButton() {

    val actions = LocalActivityBehavior.current

    TouchableOpacityButton(
        modifier = Modifier
            .padding(bottom = 25.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(9.sdp))
            .background(colorResource(id = R.color.app_color))
            .padding(vertical = 9.sdp),
        onClick = {
            actions.requestPermissions()
        }
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.continue_text),
            fontSize = 14.ssp,
            color = colorResource(id = R.color.white)
        )
    }
}