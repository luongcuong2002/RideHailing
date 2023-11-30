package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TextFieldWithBorder
import ir.kaaveh.sdpcompose.sdp

@Composable
fun UserEmailTextField(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .padding(top = 20.sdp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 7.sdp, start = 15.sdp, end = 15.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = stringResource(id = R.string.user_email_text),
                fontSize = 13.sp,
                color = colorResource(id = R.color.gray_600),
            )
            AppText(
                text = stringResource(id = R.string.user_email_advice),
                fontSize = 13.sp,
                color = colorResource(id = R.color.gray_600),
                letterSpacing = 0.1.sp,
            )
        }
        TextFieldWithBorder(
            onValueChange = {
                userInfoCreationViewModel.setUserEmail(it.text)
            },
            placeholder = stringResource(id = R.string.user_email_hint),
            textSize = 14.sp,
        )
    }
}