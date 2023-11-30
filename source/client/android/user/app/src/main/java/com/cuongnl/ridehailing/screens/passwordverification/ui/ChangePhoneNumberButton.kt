package com.cuongnl.ridehailing.screens.passwordverification.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.passwordverification.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton

@Composable
fun ChangePhoneNumberButton() {

    val actions = LocalActivityBehavior.current

    NoRippleButton(
        onClick = {
            actions.popActivity()
        }
    ) {
        AppText(
            text = stringResource(id = R.string.change_phone_number_text),
            color = colorResource(id = R.color.app_color),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}