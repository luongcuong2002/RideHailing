package com.cuongnl.ridehailing.screens.otpverification.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.OtpVerificationViewModel
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun OtpTimeout(otpVerificationViewModel: OtpVerificationViewModel = viewModel()) {
    if (otpVerificationViewModel.otpTimeoutValue.value > 0) {

        val remainingText = stringResource(id = R.string.otp_time_remaining)

        AppText(
            text = "( $remainingText ${otpVerificationViewModel.otpTimeoutValue.value}s )",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 3.dp)
                .fillMaxWidth(),
            color = colorResource(id = R.color.orange_600)
        )
    }
}