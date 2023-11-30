package com.cuongnl.ridehailing.screens.changepassword.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.ChangePasswordViewModel
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PasswordVisibilityButton(changePasswordViewModel: ChangePasswordViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .padding(top = 15.sdp)
            .clickable {
                changePasswordViewModel.setPasswordVisibility(!changePasswordViewModel.isPasswordVisible.value)
            }
    ) {
        AppText(
            text = if (changePasswordViewModel.isPasswordVisible.value)
                stringResource(id = R.string.password_invisibility_text)
            else
                stringResource(id = R.string.password_visibility_text),
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = colorResource(id = R.color.app_color),
        )
    }
}