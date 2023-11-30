package com.cuongnl.ridehailing.screens.otpverification.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.otpverification.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.OtpVerificationViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SendOtpButton(otpVerificationViewModel: OtpVerificationViewModel = viewModel()) {

    val actions = LocalActivityBehavior.current

    val textColor = if (otpVerificationViewModel.otpTimeoutValue.value > 0) {
        colorResource(id = R.color.gray_500)
    } else {
        colorResource(id = R.color.orange_600)
    }

    NoRippleButton(
        onClick = {
            if (otpVerificationViewModel.otpTimeoutValue.value == 0L) {
                actions.initiateOtp()
            }
        }
    ) {
        AppText(
            text = stringResource(id = R.string.send_otp_again),
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 40.sdp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}