package com.cuongnl.ridehailing.screens.passwordverification.ui

import OtpInputField
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.screens.passwordverification.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.PasswordVerificationViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PasswordOtpTextField(passwordVerificationViewModel: PasswordVerificationViewModel = viewModel()) {

    val actions = LocalActivityBehavior.current

    OtpInputField(
        onOtpTextChange = { otp, otpInputFilled ->
            passwordVerificationViewModel.setPasswordTextInput(otp)
            if (otpInputFilled) {
                actions.login()
            }
        },
        isTextVisible = false,
        modifier = Modifier
            .padding(horizontal = 7.sdp)
    )
}