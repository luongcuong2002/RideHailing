package com.ridehailing.driver.screens.paymentconfirmation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.kaaveh.sdpcompose.sdp
import com.ridehailing.driver.R
import com.ridehailing.driver.utils.FormatterUtils
import com.ridehailing.driver.viewmodel.PaymentConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.ssp

@Composable
fun FareAmount(
    paymentConfirmationUiViewModel: PaymentConfirmationUiViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.sdp)
            .background(colorResource(id = R.color.gray_800))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(3.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = stringResource(id = R.string.money_customer_paid),
                color = Color.White,
                fontSize = 12.ssp
            )
            AppText(
                text = FormatterUtils.formatCurrency(
                    paymentConfirmationUiViewModel.currentTripInfo.cost * 1000
                ) + " đ",
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 18.ssp
            )
        }
    }
}