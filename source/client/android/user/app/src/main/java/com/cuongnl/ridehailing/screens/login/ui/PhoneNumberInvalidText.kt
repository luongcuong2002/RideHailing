package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.login.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PhoneNumberInvalidText() {

    val actions = LocalActivityBehavior.current

    if (actions.isInvalidTextVisible()) {
        AppText(
            modifier = Modifier
                .padding(top = 7.sdp, bottom = 12.sdp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.number_phone_invalid),
            color = Color.Red,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}