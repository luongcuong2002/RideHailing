package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.newusercreation.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.ToggleableButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PasswordContinueButton(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {

    val focusManager = LocalFocusManager.current
    val actions = LocalActivityBehavior.current

    ToggleableButton(
        onClick = {
            actions.register()
            focusManager.clearFocus()
        },
        inactiveBackground = colorResource(id = R.color.orange_300),
        text = stringResource(id = R.string.continue_text),
        enable = userInfoCreationViewModel.enableUserPasswordNextButton.value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.sdp),
        dimmedWhenDisable = true,
        opacityWhenDisable = 0.4f,
    )
}