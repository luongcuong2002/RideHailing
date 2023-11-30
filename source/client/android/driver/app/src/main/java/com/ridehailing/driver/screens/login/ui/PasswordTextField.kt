package com.ridehailing.driver.screens.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.kaaveh.sdpcompose.sdp
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.LoginUiViewModel
import com.ridehailing.driver.widgets.CustomTextField

@Composable
fun PasswordTextField() {
    Row(
        modifier = Modifier
            .padding(bottom = 17.sdp)
            .padding(horizontal = 20.sdp)
            .shadow(
                10.dp,
                RoundedCornerShape(15),
            )
            .fillMaxWidth()
            .height(40.sdp)
            .clip(RoundedCornerShape(15))
            .background(Color.White)
            .padding(10.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon()
        Divider()
        TextField()
    }
}

@Composable
private fun Icon() {
    Image(
        painter = painterResource(id = R.drawable.ic_password),
        contentDescription = null,
        modifier = Modifier
            .size(20.sdp)
    )
}

@Composable
private fun Divider() {
    Box(modifier = Modifier
        .padding(horizontal = 10.sdp)
        .fillMaxHeight()
        .width(1.dp)
        .background(Color.LightGray)
    )
}

@Composable
private fun TextField(
    loginUiViewModel: LoginUiViewModel = viewModel()
) {
    CustomTextField(
        ref = loginUiViewModel.passwordTextField,
        onValueChange = {
        },
        placeholder = stringResource(id = R.string.password_placeholder),
    )
}