package com.cuongnl.ridehailing.screens.tripdetails

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.screens.tripdetails.ui.AppBar
import com.cuongnl.ridehailing.screens.tripdetails.ui.RatingDriver
import com.cuongnl.ridehailing.screens.tripdetails.ui.SendRatingButton
import com.cuongnl.ridehailing.screens.tripdetails.ui.TripDetails
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.TripDetailsUiViewModel
import ir.kaaveh.sdpcompose.sdp

class TripDetailsActivity : BaseActivity() {

    private lateinit var tripDetailsUiViewModel: TripDetailsUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        tripDetailsUiViewModel = ViewModelProvider(this)[TripDetailsUiViewModel::class.java]

        val driverAcceptResponse = intent.getSerializableExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE) as DriverAcceptResponse
        val cost = intent.getIntExtra(Constant.BUNDLE_COST, 0)

        tripDetailsUiViewModel.setDriverAcceptResponse(driverAcceptResponse)
        tripDetailsUiViewModel.setCost(cost)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.gray_300))
        ) {
            AppBar()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RatingDriver()
                SendRatingButton()
                TripDetails()
            }
        }
    }
}