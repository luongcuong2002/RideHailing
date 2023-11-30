package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PickupLocationMarker(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val markerSizeInDp = with(LocalDensity.current) {
        (Constant.MARKER_SIZE_IN_PIXEL / density).dp
    }

    Row(
        modifier = Modifier
            .padding(bottom = markerSizeInDp)
            .padding(bottom = 3.sdp)
            .width(125.sdp)
            .height(35.sdp)
            .clip(RoundedCornerShape(20))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            modifier = Modifier
                .padding(vertical = 8.sdp)
                .padding(start = 8.sdp)
                .padding(bottom = 1.sdp),
            text = bookingActivityUiViewModel.pickupLocationAddress.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp,
        )

        TouchableOpacityButton(
            modifier = Modifier
                .width(30.sdp)
                .height(35.sdp),
            onClick = {

            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .size(10.sdp)
                    .align(Alignment.Center),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray_600))
            )
        }
    }
}