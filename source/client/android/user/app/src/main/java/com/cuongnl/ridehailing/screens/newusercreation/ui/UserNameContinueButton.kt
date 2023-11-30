package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.ToggleableButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun UserNameContinueButton(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ToggleableButton(
            onClick = {
                userInfoCreationViewModel.setBottomSheetVisible(true)
            },
            inactiveBackground = colorResource(id = R.color.orange_300),
            text = stringResource(id = R.string.continue_text),
            enable = userInfoCreationViewModel.enableUserNameNextButton.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.sdp)
                .imePadding(),
            dimmedWhenDisable = true,
            opacityWhenDisable = 0.4f,
        )
    }
}