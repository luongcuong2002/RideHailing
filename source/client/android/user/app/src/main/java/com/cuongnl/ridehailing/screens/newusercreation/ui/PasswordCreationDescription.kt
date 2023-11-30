package com.cuongnl.ridehailing.screens.newusercreation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun PasswordCreationDescription() {
    AppText(
        text = stringResource(id = R.string.password_creation_description),
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
        color = Color.Black,
        textAlign = TextAlign.Left,
        lineHeight = 20.sp,
        modifier = Modifier
            .padding(top = 10.dp)
    )
}