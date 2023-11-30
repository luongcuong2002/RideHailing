package com.cuongnl.ridehailing.screens.passwordverification.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun HelloText() {
    AppText(
        text = stringResource(id = R.string.hello_text),
        fontSize = 28.sp,
        color = Color.Black,
    )
}