package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordCreationBottomSheet(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {

    if (userInfoCreationViewModel.isPasswordCreationBottomSheetVisible.value) {

        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        LaunchedEffect(userInfoCreationViewModel.isPasswordCreationBottomSheetVisible.value) {
            if (userInfoCreationViewModel.isPasswordCreationBottomSheetVisible.value) {
                sheetState.show()
            } else {
                sheetState.hide()
            }
        }

        ModalBottomSheet(
            onDismissRequest = {
                userInfoCreationViewModel.setBottomSheetVisible(false)
            },
            sheetState = sheetState,
            windowInsets = WindowInsets.navigationBars,
            modifier = Modifier.navigationBarsPadding(),
            containerColor = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.sdp)
            ) {
                PasswordCreationTitle()
                PasswordCreationDescription()
                PasswordTextField()
                PasswordVisibilityButton()
                PasswordContinueButton()
            }
        }
    }
}