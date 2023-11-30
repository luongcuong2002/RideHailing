package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SelectCountryCodeText() {
    AppText(
        text = stringResource(id = R.string.select_country_code_text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.sdp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}