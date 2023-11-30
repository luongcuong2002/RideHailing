package com.ridehailing.driver.screens.dropoffconfirmation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.utils.FormatterUtils
import com.ridehailing.driver.viewmodel.DropoffConfirmationUiViewModel
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun FareAmount(
    dropoffConfirmationUiViewModel: DropoffConfirmationUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = 20.sdp)
            .fillMaxWidth()
    ) {
        AppText(
            text = stringResource(id = R.string.expected_fare),
            fontSize = 12.ssp,
            color = Color.White,
        )

        Row(
            modifier = Modifier
                .padding(top = 10.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextItem(
                value = dropoffConfirmationUiViewModel.currentTripInfo.distanceInKilometers.toString(),
                unit = "km"
            )
            TextItem(
                value = FormatterUtils.formatCurrency(
                    dropoffConfirmationUiViewModel.currentTripInfo.cost * 1000
                ),
                unit = "đ"
            )
            TextItem(
                value = dropoffConfirmationUiViewModel.currentTripInfo.durationInMinutes.toString(),
                unit = "phút"
            )
        }
    }
}

@Composable
private fun TextItem(
    value: String,
    unit: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = value,
            fontSize = 16.ssp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        AppText(
            text = unit,
            fontSize = 10.ssp,
            color = Color.White,
        )
    }
}