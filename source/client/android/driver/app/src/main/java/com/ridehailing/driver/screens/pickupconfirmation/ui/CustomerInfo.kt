package com.ridehailing.driver.screens.pickupconfirmation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun CustomerInfo(
    pickupConfirmationUiViewModel: PickupConfirmationUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = 20.sdp)
            .fillMaxWidth()
    ) {
        AppText(
            text = stringResource(id = R.string.customer_info),
            fontSize = 12.ssp,
            color = Color.White,
        )

        Column(
            modifier = Modifier
                .padding(top = 10.sdp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.sdp)
        ) {
            TextItem(value = pickupConfirmationUiViewModel.currentTripInfo.userInfo.userName)
            TextItem(value = pickupConfirmationUiViewModel.currentTripInfo.userInfo.phoneNumber)
        }
    }
}

@Composable
private fun TextItem(
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .padding(end = 5.sdp, top = 3.sdp)
                .size(4.sdp)
                .clip(CircleShape)
                .background(Color.White)
        )
        AppText(
            text = value,
            fontSize = 12.ssp,
            color = Color.White,
            fontWeight = FontWeight.Light
        )
    }
}