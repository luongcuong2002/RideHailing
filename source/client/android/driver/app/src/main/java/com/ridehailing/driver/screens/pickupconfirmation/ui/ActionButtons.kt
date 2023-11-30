package com.ridehailing.driver.screens.pickupconfirmation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ActionButtons(
    pickupConfirmationUiViewModel: PickupConfirmationUiViewModel = viewModel()
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(top = 30.sdp)
    ) {
        Button(
            icon = painterResource(id = R.drawable.confirm_pickup_ic_user),
            text = pickupConfirmationUiViewModel.currentTripInfo.userInfo.userName,
        ) {

        }
        Button(
            icon = painterResource(id = R.drawable.confirm_pickup_ic_message),
            text = stringResource(id = R.string.texting),
        ) {
            pickupConfirmationUiViewModel.onClickTextingButton(context)
        }
        Button(
            icon = painterResource(id = R.drawable.confirm_pickup_ic_call),
            text = stringResource(id = R.string.call),
        ) {
            pickupConfirmationUiViewModel.onClickCallButton(context)
        }
        Button(
            icon = painterResource(id = R.drawable.confirm_pickup_ic_close),
            text = stringResource(id = R.string.cancel_trip),
        ) {
            pickupConfirmationUiViewModel.onClickCancelTripButton(context)
        }
    }
}

@Composable
private fun RowScope.Button(
    icon: Painter,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 5.sdp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        NoRippleButton(
            onClick = onClick
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(35.sdp)
            )
        }

        AppText(
            text = text,
            fontSize = 10.ssp,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}