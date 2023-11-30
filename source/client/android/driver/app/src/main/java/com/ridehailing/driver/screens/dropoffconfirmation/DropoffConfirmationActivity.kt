package com.ridehailing.driver.screens.dropoffconfirmation

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
import com.ridehailing.driver.screens.dropoffconfirmation.ui.ActionButtons
import com.ridehailing.driver.screens.dropoffconfirmation.ui.CustomerInfo
import com.ridehailing.driver.screens.dropoffconfirmation.ui.FareAmount
import com.ridehailing.driver.screens.dropoffconfirmation.ui.NoteForDriver
import com.ridehailing.driver.screens.dropoffconfirmation.ui.DropoffAddressTextAndDirectionButton
import com.ridehailing.driver.screens.dropoffconfirmation.ui.DropoffLocationText
import com.ridehailing.driver.screens.dropoffconfirmation.ui.SwipeButton
import com.ridehailing.driver.theme.AppTheme
import com.ridehailing.driver.utils.Constant
import com.ridehailing.driver.viewmodel.DropoffConfirmationUiViewModel
import ir.kaaveh.sdpcompose.sdp

class DropoffConfirmationActivity : BaseActivity() {

    private lateinit var dropoffConfirmationUiViewModel: DropoffConfirmationUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        dropoffConfirmationUiViewModel = ViewModelProvider(this)[DropoffConfirmationUiViewModel::class.java]

        val tripInfo = intent.getSerializableExtra(Constant.BUNDLE_TRIP_INFO) as TripInfo
        dropoffConfirmationUiViewModel.setCurrentTripInfo(tripInfo)
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
                DropoffLocationText()
                DropoffAddressTextAndDirectionButton()
                ActionButtons()
                FareAmount()
                CustomerInfo()
                NoteForDriver()
            }
            SwipeButton()
        }
    }
}