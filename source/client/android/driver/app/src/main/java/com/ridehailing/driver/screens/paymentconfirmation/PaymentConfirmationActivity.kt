package com.ridehailing.driver.screens.paymentconfirmation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.screens.paymentconfirmation.ui.ConfirmButton
import com.ridehailing.driver.screens.paymentconfirmation.ui.FareAmount
import com.ridehailing.driver.screens.paymentconfirmation.ui.TripSummary
import com.ridehailing.driver.theme.AppTheme
import com.ridehailing.driver.utils.Constant
import com.ridehailing.driver.viewmodel.DropoffConfirmationUiViewModel
import com.ridehailing.driver.viewmodel.PaymentConfirmationUiViewModel

class PaymentConfirmationActivity : BaseActivity() {

    private lateinit var paymentConfirmationUiViewModel: PaymentConfirmationUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        paymentConfirmationUiViewModel = ViewModelProvider(this)[PaymentConfirmationUiViewModel::class.java]

        val tripInfo = intent.getSerializableExtra(Constant.BUNDLE_TRIP_INFO) as TripInfo
        paymentConfirmationUiViewModel.setCurrentTripInfo(tripInfo)

        paymentConfirmationUiViewModel.setupListeners(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        paymentConfirmationUiViewModel.removeListeners()
    }
}

@Composable
private fun Screen() {
    AppTheme(
        withImageBackground = false
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                FareAmount()
                TripSummary()
            }
            ConfirmButton()
        }
    }
}