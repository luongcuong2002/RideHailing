package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.login.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun PolicyText() {

    val actions = LocalActivityBehavior.current

    AppText(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.gray_500),
                    fontSize = 12.sp
                )
            ) {
                append(stringResource(id = R.string.policy_text_part_1))
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.app_color),
                    fontSize = 12.sp
                )
            ) {
                append(stringResource(id = R.string.policy_text_part_2))
            }
        },
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures {
                    actions.openPolicy()
                }
            },
        textAlign = TextAlign.Center,
        lineHeight = 16.ssp
    )
}