package com.cuongnl.ridehailing.screens.selectinglocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.SelectingLocationType
import com.cuongnl.ridehailing.extensions.shimmerEffect
import com.cuongnl.ridehailing.viewmodel.SelectingLocationUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun FetchAddressResponses(
    modifier: Modifier = Modifier,
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel(),
) {

    val shouldVisible =
        selectingLocationUiViewModel.isAddressResponsesVisible.value && (when (selectingLocationUiViewModel.currentAddressType.value) {
            SelectingLocationType.PICKUP_LOCATION -> selectingLocationUiViewModel.pickupTextField.value.text.isNotEmpty()
            SelectingLocationType.DESTINATION_LOCATION -> selectingLocationUiViewModel.destinationTextField.value.text.isNotEmpty()
        })

    if (shouldVisible) {

        val size = if (selectingLocationUiViewModel.isFetchingAddressPredictions.value) {
            5
        } else {
            selectingLocationUiViewModel.addressPredictions.size
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(bottomEnd = 10.sdp, bottomStart = 10.sdp))
                .background(Color.White)
                .padding(top = 20.sdp)
                .padding(horizontal = 10.sdp),
            content = {
                items(size) {

                    val isLoading = selectingLocationUiViewModel.isFetchingAddressPredictions.value

                    AddItem(it, isLoading)
                }
            },
        )
    }
}

@Composable
private fun AddItem(
    index: Int,
    isLoading: Boolean = false,
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val item = if (isLoading) {
        null
    } else {
        selectingLocationUiViewModel.addressPredictions[index]
    }

    NoRippleButton(onClick = {
        if (!isLoading) {
            selectingLocationUiViewModel.onClickAddressPredictionResponse(context, item!!)
        }
    }) {
        Column {
            Row(
                modifier = Modifier.padding(7.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {

                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .size(18.sdp)
                            .shimmerEffect()
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = null,
                        modifier = Modifier.size(18.sdp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.height(40.dp)
                ) {

                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .width(75.sdp)
                                .height(18.dp)
                                .shimmerEffect()
                        )
                    } else {
                        AppText(
                            text = item!!.getPrimaryText(null).toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .width(150.sdp)
                                .height(18.dp)
                                .shimmerEffect()
                        )
                    } else {
                        AppText(
                            text = item!!.getFullText(null).toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = colorResource(id = R.color.gray_700)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(colorResource(id = R.color.gray_200))
            )
        }
    }
}