package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun PasswordVisibilityButton(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .clickable {
                userInfoCreationViewModel.setPasswordVisibility(!userInfoCreationViewModel.isPasswordVisible.value)
            }
    ) {
        AppText(
            text = if (userInfoCreationViewModel.isPasswordVisible.value)
                stringResource(id = R.string.password_invisibility_text)
            else
                stringResource(id = R.string.password_visibility_text),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = colorResource(id = R.color.app_color),
        )
    }
}