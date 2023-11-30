package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.shimmerEffect
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun Vehicles(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val data = bookingActivityUiViewModel.bookingsInfo.size

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        content = {
            items(data) { index ->
                AddItem(index)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(15.sdp),
        contentPadding = PaddingValues(horizontal = 15.sdp)
    )
}

@Composable
private fun AddItem(
    index: Int,
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val item = bookingActivityUiViewModel.bookingsInfo[index]

    val backgroundColor = if (item.isSelected.value) {
        colorResource(id = R.color.orange_50).copy(alpha = 0.7f)
    } else {
        colorResource(id = R.color.gray_200).copy(alpha = 0.5f)
    }

    val borderColor = if (item.isSelected.value) {
        colorResource(id = R.color.orange_200)
    } else {
        Color.Transparent
    }

    NoRippleButton(
        onClick = {
            bookingActivityUiViewModel.selectBookingInfoAndUpdateUI(context, item.transportationType)
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .width(150.sdp)
                .clip(RoundedCornerShape(10.sdp))
                .border(
                    width = 1.sdp,
                    color = borderColor,
                    shape = RoundedCornerShape(10.sdp)
                )
                .background(backgroundColor)
                .padding(10.sdp)
        ) {

            val (
                vehicleName,
                detailIcon,
                vehicleCharacteristic,
                fareAmount,
                timeToDriverArrival,
                vehicleImage
            ) = createRefs()

            VehicleName(
                modifier = Modifier
                    .constrainAs(vehicleName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(detailIcon.start)
                        width = Dimension.fillToConstraints
                    },
                name = item.transportationType.globalName
            )

            VehicleCharacteristic(
                modifier = Modifier
                    .constrainAs(vehicleCharacteristic) {
                        top.linkTo(vehicleName.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(detailIcon.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 1.sdp),
                text = stringResource(item.transportationType.characteristicText)
            )

            DetailIcon(
                modifier = Modifier
                    .constrainAs(detailIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 2.sdp),
                index = index
            )

            FareAmount(
                modifier = Modifier
                    .constrainAs(fareAmount) {
                        top.linkTo(vehicleCharacteristic.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 8.sdp),
                fareAmount = item.bookingInfoResponse?.fareAmount ?: -1
            )

            TimeToDriverArrival(
                modifier = Modifier
                    .constrainAs(timeToDriverArrival) {
                        top.linkTo(fareAmount.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 1.sdp),
                minutes = item.bookingInfoResponse?.minutesToDriverArrival ?: -1
            )

            VehicleImage(
                modifier = Modifier
                    .constrainAs(vehicleImage) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                image = item.transportationType.icon
            )
        }
    }
}

@Composable
private fun VehicleName(
    modifier: Modifier = Modifier,
    name: String
) {
    AppText(
        modifier = modifier,
        text = name,
        fontSize = 13.ssp,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(id = R.color.app_color),
    )
}

@Composable
private fun DetailIcon(
    modifier: Modifier = Modifier,
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel(),
    index: Int
) {
    Box(
        modifier = modifier
    ) {
        TouchableOpacityButton(
            onClick = {
                if (bookingActivityUiViewModel.bookingsInfo[index].bookingInfoResponse != null) {
                    bookingActivityUiViewModel.setFareCalculationInfoSelectedIndex(index)
                    bookingActivityUiViewModel.setIsBottomSheetVisible(true)
                }
            }
        ) {
            Image(
                modifier = modifier
                    .size(16.sdp),
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun VehicleCharacteristic(
    modifier: Modifier = Modifier,
    text: String
) {
    AppText(
        modifier = modifier,
        text = text,
        fontSize = 9.ssp,
        color = colorResource(id = R.color.gray_700),
    )
}

@Composable
private fun FareAmount(
    modifier: Modifier = Modifier,
    fareAmount: Int
) {
    if (fareAmount < 0) {
        Box(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 5.sdp)
                    .width(35.sdp)
                    .height(15.sdp)
                    .shimmerEffect()
            )
        }
    } else {
        AppText(
            modifier = modifier.height(20.sdp),
            text = fareAmount.toString() + "k",
            fontSize = 15.ssp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            letterSpacing = 0.03.sp
        )
    }
}

@Composable
private fun TimeToDriverArrival(
    modifier: Modifier = Modifier,
    minutes: Int
) {
    if (minutes < 0) {
        Box(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .width(70.sdp)
                    .height(12.sdp)
                    .shimmerEffect()
            )
        }
    } else {
        AppText(
            modifier = modifier.height(12.sdp),
            text = minutes.toString() + " " + stringResource(id = R.string.minutes_text),
            fontSize = 9.ssp,
            color = colorResource(id = R.color.gray_700),
        )
    }
}

@Composable
private fun VehicleImage(
    modifier: Modifier = Modifier,
    image: Int
) {
    Image(
        modifier = modifier
            .size(50.sdp)
            .offset(x = 6.sdp, y = 10.sdp),
        painter = painterResource(id = image),
        contentDescription = null
    )
}