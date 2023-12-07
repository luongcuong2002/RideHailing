package com.ridehailing.driver.screens.confirmlocation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.ConfirmLocationViewModel
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ConfirmButton(
    confirmLocationViewModel: ConfirmLocationViewModel = viewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(bottom = 5.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(colorResource(id = R.color.app_color))
            .clickable {
                confirmLocationViewModel.clickConfirmButton(context)
            }
            .padding(vertical = 11.sdp),
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.confirm_text),
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Medium
        )
    }

}