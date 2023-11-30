package com.cuongnl.ridehailing.screens.confirmlocation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.ConfirmLocationState
import com.cuongnl.ridehailing.extensions.shimmerEffect
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.ConfirmLocationViewModel
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun RowScope.AddressView(confirmLocationViewModel: ConfirmLocationViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {

        val address = when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {
                confirmLocationViewModel.destinationLocationAddress.value
            }

            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                confirmLocationViewModel.pickupLocationAddress.value
            }
        }

        if (address != null && !confirmLocationViewModel.isAddressLoading.value) {
            AppText(
                text = FormatterUtils.getShortAddress(address),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
            AppText(
                text = address,
                fontSize = 13.sp,
                color = colorResource(id = R.color.gray_700)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect()
            )
        }
    }
}