package com.ridehailing.driver.screens.pickupconfirmation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun PickupAddressTextAndDirectionButton(
    pickupConfirmationUiViewModel: PickupConfirmationUiViewModel = viewModel()
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(top = 10.sdp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        AppText(
            text = pickupConfirmationUiViewModel.currentTripInfo.pickupAddress.address,
            fontSize = 15.ssp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
        )

        TouchableOpacityButton(
            modifier = Modifier
                .clip(RoundedCornerShape(5.sdp))
                .background(colorResource(id = R.color.orange_400))
                .padding(horizontal = 10.sdp, vertical = 5.sdp),
            onClick = {
                pickupConfirmationUiViewModel.onClickDirectionButton(context)
            }
        ) {
            AppText(
                text = stringResource(id = R.string.directions),
                fontSize = 12.ssp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}