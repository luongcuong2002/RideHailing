package com.ridehailing.driver.screens.paymentconfirmation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.PaymentConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BoxScope.ConfirmButton(
    paymentConfirmationUiViewModel: PaymentConfirmationUiViewModel = viewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(top = 10.sdp)
            .padding(horizontal = 10.sdp)
            .fillMaxWidth()
    ) {
        TouchableOpacityButton(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20))
                .background(colorResource(id = R.color.blue_500))
                .padding(vertical = 10.sdp),
            onClick = {
                paymentConfirmationUiViewModel.clickConfirmButton(context)
            }
        ) {
            AppText(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(id = R.string.confirm_paid),
                color = Color.White,
                fontSize = 12.ssp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}