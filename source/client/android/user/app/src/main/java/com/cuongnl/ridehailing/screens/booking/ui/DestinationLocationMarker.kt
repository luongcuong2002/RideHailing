package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DestinationLocationMarker(
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
            .height(40.sdp)
            .clip(RoundedCornerShape(20))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .padding(5.sdp)
                .clip(RoundedCornerShape(20))
                .background(colorResource(id = R.color.app_color))
                .padding(2.sdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppText(
                text = "9",
                fontSize = 8.ssp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                lineHeight = 6.ssp,
                maxLines = 1
            )
            AppText(
                text = "min",
                fontSize = 8.ssp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                lineHeight = 6.ssp,
                maxLines = 1
            )
        }

        AppText(
            modifier = Modifier
                .weight(1f),
            text = bookingActivityUiViewModel.destinationLocationAddress.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 9.ssp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 11.ssp,
        )

        TouchableOpacityButton(
            modifier = Modifier
                .width(30.sdp)
                .fillMaxHeight(),
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