package com.cuongnl.ridehailing.screens.passwordverification.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.PasswordVerificationViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun WrongPasswordText(passwordVerificationViewModel: PasswordVerificationViewModel = viewModel()) {
    if (passwordVerificationViewModel.isWrongPassword.value) {
        AppText(
            text = stringResource(id = R.string.wrong_password_text),
            color = Color.Red,
            fontSize = 11.sp,
            modifier = Modifier
                .padding(top = 8.sdp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}