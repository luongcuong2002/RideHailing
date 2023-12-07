package com.ridehailing.driver.screens.confirmlocation.ui

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
import com.ridehailing.driver.R
import com.ridehailing.driver.extensions.shimmerEffect
import com.ridehailing.driver.utils.FormatterUtils
import com.ridehailing.driver.viewmodel.ConfirmLocationViewModel
import com.ridehailing.driver.widgets.AppText

@Composable
fun RowScope.AddressView(confirmLocationViewModel: ConfirmLocationViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {

        val address = confirmLocationViewModel.locationAddress.value

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