package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun UserNameTextField(userInfoCreationViewModel: UserInfoCreationViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .padding(top = 20.sdp)
    ) {
        Row {
            AppText(
                text = stringResource(id = R.string.user_name_text),
                fontSize = 13.sp,
                color = colorResource(id = R.color.gray_600),
                modifier = Modifier
                    .padding(bottom = 7.sdp, start = 15.sdp, end = 2.sdp),
            )
            AppText(
                text = "*",
                color = Color.Red,
                fontSize = 13.sp
            )
        }
        TextFieldWithBorder(
            onValueChange = {
                userInfoCreationViewModel.setUserName(it.text)
            },
            placeholder = stringResource(id = R.string.user_name_hint),
            textSize = 14.sp,
        )
    }
}