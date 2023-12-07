package com.ridehailing.driver.screens.pickupconfirmation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModelProvider
import com.ridehailing.driver.R
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.screens.pickupconfirmation.ui.ActionButtons
import com.ridehailing.driver.screens.pickupconfirmation.ui.CustomerInfo
import com.ridehailing.driver.screens.pickupconfirmation.ui.FareAmount
import com.ridehailing.driver.screens.pickupconfirmation.ui.NoteForDriver
import com.ridehailing.driver.screens.pickupconfirmation.ui.PickupAddressTextAndDirectionButton
import com.ridehailing.driver.screens.pickupconfirmation.ui.PickupLocationText
import com.ridehailing.driver.screens.pickupconfirmation.ui.SwipeButton
import com.ridehailing.driver.theme.AppTheme
import com.ridehailing.driver.utils.Constant
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import ir.kaaveh.sdpcompose.sdp

class PickupConfirmationActivity : BaseActivity() {

    private lateinit var pickupConfirmationViewModel: PickupConfirmationUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        pickupConfirmationViewModel = ViewModelProvider(this)[PickupConfirmationUiViewModel::class.java]

        val tripInfo = intent.getSerializableExtra(Constant.BUNDLE_TRIP_INFO) as TripInfo
        pickupConfirmationViewModel.setCurrentTripInfo(tripInfo)

        pickupConfirmationViewModel.setupListeners(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        pickupConfirmationViewModel.removeListeners()
    }

    override fun onBackPressed() {

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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.gray_800))
                    .padding(10.sdp)
            ) {
                PickupLocationText()
                PickupAddressTextAndDirectionButton()
                ActionButtons()
                FareAmount()
                CustomerInfo()
                NoteForDriver()
            }
            SwipeButton()
        }
    }
}