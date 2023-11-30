package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.login.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.ToggleableButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ContinueButton() {

    val focusManager = LocalFocusManager.current
    val actions = LocalActivityBehavior.current

    ToggleableButton(
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .fillMaxWidth(),
        onClick = {
            actions.onContinueButtonClicked()
            focusManager.clearFocus()
        },
        text = stringResource(id = R.string.continue_text),
        enable = actions.canClickContinueButton(),
    )
}