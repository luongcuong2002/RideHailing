package com.cuongnl.ridehailing.screens.newusercreation.ui

import OtpInputField
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PasswordTextField(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {
    OtpInputField(
        modifier = Modifier
            .padding(vertical = 30.sdp),
        onOtpTextChange = { otp, _ ->
            userInfoCreationViewModel.setUserPassword(otp)
        },
        isTextVisible = userInfoCreationViewModel.isPasswordVisible.value,
    )
}