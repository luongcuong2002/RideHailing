package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun PasswordCreationTitle() {
    AppText(
        text = stringResource(id = R.string.password_creation_title),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.1.sp,
        color = Color.Black,
    )
}