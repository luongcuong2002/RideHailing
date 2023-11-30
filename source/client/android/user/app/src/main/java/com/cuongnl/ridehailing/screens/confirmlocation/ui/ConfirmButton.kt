package com.cuongnl.ridehailing.screens.confirmlocation.ui

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.ConfirmLocationState
import com.cuongnl.ridehailing.screens.confirmlocation.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.ConfirmLocationViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ConfirmButton(
    confirmLocationViewModel: ConfirmLocationViewModel = viewModel()
) {

    val actions = LocalActivityBehavior.current

    val buttonText = when (confirmLocationViewModel.confirmLocationState.value) {
        ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> stringResource(id = R.string.confirm_destination_location)
        ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> stringResource(id = R.string.confirm_pickup_location)
    }

    Box(
        modifier = Modifier
            .padding(bottom = 5.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(colorResource(id = R.color.app_color))
            .clickable {
                actions.onClickConfirmButton()
            }
            .padding(vertical = 11.sdp),
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = buttonText,
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Medium
        )
    }

}