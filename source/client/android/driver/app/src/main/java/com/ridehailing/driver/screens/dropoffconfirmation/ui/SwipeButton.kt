package com.ridehailing.driver.screens.dropoffconfirmation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.DropoffConfirmationUiViewModel
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.SwipeableView
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BoxScope.SwipeButton(
    dropoffConfirmationUiViewModel: DropoffConfirmationUiViewModel = viewModel()
) {
    SwipeableView(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 10.sdp)
            .padding(horizontal = 10.sdp),
        background = {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 5.sdp,
                        shape = CircleShape
                    )
                    .fillMaxWidth()
                    .height(45.sdp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.red_200).copy(0.3f))
            ) {
                AppText(
                    text = stringResource(id = R.string.swipe_to_confirm_dropoff),
                    fontSize = 12.ssp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        },
        thumb = {
            Box(
                modifier = Modifier
                    .size(45.sdp)
                    .shadow(
                        elevation = 5.sdp,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.red_400))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(11.sdp)
                        .fillMaxSize()
                )
            }
        },
        progressTint = colorResource(id = R.color.red_400).copy(0.5f),
        onSwiped = {
            dropoffConfirmationUiViewModel.onSwipeToConfirm()
        }
    )
}