package com.cuongnl.ridehailing.screens.changepassword.ui

import OtpInputField
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.ChangePasswordViewModel

@Composable
fun PasswordTextField(changePasswordViewModel: ChangePasswordViewModel = viewModel()) {
    OtpInputField(
        onOtpTextChange = { otp, _ ->
            changePasswordViewModel.setPasswordInputText(otp)
        },
        isTextVisible = changePasswordViewModel.isPasswordVisible.value,
    )
}