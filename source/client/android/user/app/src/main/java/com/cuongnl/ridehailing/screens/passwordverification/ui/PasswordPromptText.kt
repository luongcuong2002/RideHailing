package com.cuongnl.ridehailing.screens.passwordverification.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.PasswordVerificationViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PasswordPromptText(passwordVerificationViewModel: PasswordVerificationViewModel = viewModel()) {
    AppText(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp
                )
            ) {
                append(stringResource(id = R.string.password_prompt_text))
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp
                )
            ) {
                append(passwordVerificationViewModel.internationalPhoneNumber.value)
            }
        },
        modifier = Modifier
            .padding(top = 5.sdp, bottom = 35.sdp),
        lineHeight = 22.sp,
        letterSpacing = 0.1.sp
    )
}