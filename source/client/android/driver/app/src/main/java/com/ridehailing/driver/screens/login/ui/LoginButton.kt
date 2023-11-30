package com.ridehailing.driver.screens.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.TouchableOpacityButton
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.LoginUiViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun LoginButton(
    loginUiViewModel: LoginUiViewModel = viewModel()
) {

    val context = LocalContext.current

    TouchableOpacityButton(
        modifier = Modifier
            .width(120.sdp)
            .height(34.sdp)
            .clip(RoundedCornerShape(15))
            .border(
                width = 1.sdp,
                color = Color.White,
                shape = RoundedCornerShape(15)
            )
            .background(Color.Green),
        onClick = {
            loginUiViewModel.clickLoginButton(context)
        }
    ) {
        AppText(
            text = stringResource(id = R.string.login_text),
            fontSize = 12.ssp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}