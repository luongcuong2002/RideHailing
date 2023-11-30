package com.cuongnl.ridehailing.screens.notefordriver.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.notefordriver.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BoxScope.DoneButton() {

    val actions = LocalActivityBehavior.current

    TouchableOpacityButton(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 10.sdp)
            .padding(horizontal = 15.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(colorResource(id = R.color.app_color))
            .padding(vertical = 10.sdp),
        onClick = {
            actions.clickDone()
        }
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.done),
            fontSize = 14.ssp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Medium
        )
    }
}