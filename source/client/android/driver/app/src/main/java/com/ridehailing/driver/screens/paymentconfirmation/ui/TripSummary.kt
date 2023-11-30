package com.ridehailing.driver.screens.paymentconfirmation.ui

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
import com.ridehailing.driver.viewmodel.PaymentConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import com.ridehailing.driver.R
import com.ridehailing.driver.utils.FormatterUtils

@Composable
fun TripSummary(
    paymentConfirmationUiViewModel: PaymentConfirmationUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = 16.sdp)
            .padding(horizontal = 10.sdp)
            .fillMaxWidth()
    ) {
        TextRow(
            leading = stringResource(id = R.string.payment_method),
            trailing = paymentConfirmationUiViewModel.currentTripInfo.paymentMethod
        )
        TextRow(
            leading = stringResource(id = R.string.trip_cost),
            trailing = FormatterUtils.formatCurrency(
                paymentConfirmationUiViewModel.currentTripInfo.cost * 1000
            ) + " đ"
        )
        TextRow(
            leading = stringResource(id = R.string.total_distance),
            trailing = paymentConfirmationUiViewModel.currentTripInfo.distanceInKilometers.toString() + " km"
        )
    }
}

@Composable
private fun TextRow(
    leading: String,
    trailing: String
) {
    Row(
        modifier = Modifier
            .padding(vertical = 5.sdp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = leading,
            fontSize = 12.ssp,
            color = Color.Black,
        )

        AppText(
            text = trailing,
            fontSize = 12.ssp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}