package com.cuongnl.ridehailing.screens.changepassword.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.changepassword.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.ChangePasswordViewModel
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.ToggleableButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ContinueButton(changePasswordViewModel: ChangePasswordViewModel = viewModel()) {

    val actions = LocalActivityBehavior.current

    ToggleableButton(
        onClick = {
            actions.changePassword()
        },
        inactiveBackground = colorResource(id = R.color.orange_300),
        text = stringResource(id = R.string.finish_text),
        enable = changePasswordViewModel.enableUserPasswordNextButton.value,
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .imePadding(),
        dimmedWhenDisable = true,
        opacityWhenDisable = 0.4f,
    )
}