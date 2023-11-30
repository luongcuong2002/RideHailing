package com.cuongnl.ridehailing.screens.selectinglocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.SelectingLocationType
import com.cuongnl.ridehailing.viewmodel.SelectingLocationUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.CustomTextField
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SearchingLocation(
    modifier: Modifier = Modifier
) {
    
    Column(
        modifier = modifier
            .padding(top = 20.sdp)
            .shadow(
                elevation = 7.dp,
                shape = RoundedCornerShape(10.sdp)
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(color = colorResource(id = R.color.gray_200))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.sdp))
                .background(Color.White)
                .padding(top = 10.sdp)
                .padding(horizontal = 10.sdp)
        ) {
            PickupSearchBar()
            DestinationSearchBar()
        }

        ChoosingButton()
    }
}

@Composable
private fun PickupSearchBar(
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel(),
) {

    LaunchedEffect(null) {
        if (selectingLocationUiViewModel.currentAddressType.value == SelectingLocationType.PICKUP_LOCATION) {
            selectingLocationUiViewModel.pickupFocusRequester.requestFocus()
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(6.sdp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons_pickuppoint),
                contentDescription = null,
                modifier = Modifier
                    .size(20.sdp)
            )

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                AppText(
                    text = stringResource(id = R.string.your_location),
                    color = colorResource(id = R.color.gray_800),
                    fontSize = 12.sp,
                )

                CustomTextField(
                    ref = selectingLocationUiViewModel.getPickupTextFieldState(),
                    focusRequester = selectingLocationUiViewModel.pickupFocusRequester,
                    onValueChange = {
                        val text = it.text.trim()
                        selectingLocationUiViewModel.onPickupTextChange(text)
                    },
                    onFocusChanged = {
                        if (it.hasFocus) {
                            selectingLocationUiViewModel.setCurrentAddressType(SelectingLocationType.PICKUP_LOCATION)
                        }
                    },
                    selectAllOnFocus = true,
                    textSize = 14.sp,
                    placeholder = stringResource(id = R.string.enter_pickup_placeholder),
                    fontWeight = FontWeight.Medium,
                    textColor = colorResource(id = R.color.black),
                )
            }
        }
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(id = R.color.gray_300))
        )
    }
}

@Composable
private fun DestinationSearchBar(
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel(),
) {

    LaunchedEffect(null) {
        if (selectingLocationUiViewModel.currentAddressType.value == SelectingLocationType.DESTINATION_LOCATION) {
            selectingLocationUiViewModel.destinationFocusRequester.requestFocus()
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp),
            modifier = Modifier.padding(vertical = 10.sdp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons_dropoffpoint),
                contentDescription = null,
                modifier = Modifier
                    .size(20.sdp)
            )

            CustomTextField(
                ref = selectingLocationUiViewModel.getDestinationTextFieldState(),
                focusRequester = selectingLocationUiViewModel.destinationFocusRequester,
                onValueChange = {
                    val text = it.text.trim()
                    selectingLocationUiViewModel.onDestinationTextChange(text)
                },
                onFocusChanged = {
                    if (it.hasFocus) {
                        selectingLocationUiViewModel.setCurrentAddressType(SelectingLocationType.DESTINATION_LOCATION)
                    }
                },
                selectAllOnFocus = true,
                textSize = 14.sp,
                placeholder = stringResource(id = R.string.enter_destination_placeholder),
                fontWeight = FontWeight.Medium,
                textColor = colorResource(id = R.color.black),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(id = R.color.orange_400))
        )
    }
}

@Composable
private fun ChoosingButton(
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel(),
) {

    val text = when (selectingLocationUiViewModel.currentAddressType.value) {
        SelectingLocationType.DESTINATION_LOCATION -> stringResource(id = R.string.choose_your_destination)
        SelectingLocationType.PICKUP_LOCATION -> stringResource(id = R.string.choose_your_pickup)
    }

    TouchableOpacityButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons_pickinmap),
                contentDescription = null,
                modifier = Modifier
                    .size(15.sdp)
            )
            AppText(
                text = text,
                fontSize = 12.sp,
            )
        }
    }
}