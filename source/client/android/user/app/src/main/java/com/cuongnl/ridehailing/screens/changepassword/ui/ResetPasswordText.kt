package com.cuongnl.ridehailing.screens.changepassword.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ResetPasswordText() {
    AppText(
        text = stringResource(R.string.reset_password_text),
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier
            .padding(bottom = 30.sdp)
    )
}