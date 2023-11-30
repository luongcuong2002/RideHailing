package com.cuongnl.ridehailing.screens.selectinglocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.SelectingLocationType
import com.cuongnl.ridehailing.screens.selectinglocation.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.SelectingLocationUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        BackButton()
        Title()
    }
}

@Composable
private fun BackButton() {

    val actions = LocalActivityBehavior.current

    NoRippleButton(
        onClick = {
            actions.onClickBackButton()
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .size(18.sdp)
        )
    }
}

@Composable
private fun Title(
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel()
) {

    val text = when (selectingLocationUiViewModel.currentAddressType.value) {
        SelectingLocationType.DESTINATION_LOCATION -> stringResource(id = R.string.select_pickup)
        SelectingLocationType.PICKUP_LOCATION -> stringResource(id = R.string.select_destination)
    }

    AppText(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Black,
    )
}