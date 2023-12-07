package com.ridehailing.driver.screens.home.tab.trip.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ridehailing.driver.R
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.screens.home.LocalHomeViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ColumnScope.TripList() {

    val homeUiViewModel = LocalHomeViewModel.current

    LazyColumn(
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .fillMaxWidth()
            .weight(1f)
            .padding(horizontal = 10.sdp),
        content = {
            items(homeUiViewModel.getTrips().size) { index ->
                TripItem(
                    trip = homeUiViewModel.getTrips()[index]
                )
            }
        },
        contentPadding = PaddingValues(vertical = 20.sdp)
    )
}

@Composable
private fun TripItem(
    trip: TripInfo
) {

    val homeUiViewModel = LocalHomeViewModel.current

    NoRippleButton(
        onClick = {
            homeUiViewModel.setSelectedTrip(trip)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.sdp)
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(10.sdp)
                )
                .clip(RoundedCornerShape(10.sdp))
                .background(Color.White)
                .padding(10.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Address(
                icon = painterResource(id = R.drawable.ic_pickup),
                address = trip.pickupAddress.address
            )

            Row(
                modifier = Modifier
                    .padding(vertical = 4.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.sdp)
            ) {
                AppText(
                    text = trip.distanceInKilometers.toString() + " km",
                    fontSize = 8.ssp,
                    color = colorResource(id = R.color.gray_600),
                )

                Image(
                    modifier = Modifier
                        .width(20.sdp)
                        .height(23.sdp),
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                )

                AppText(
                    text = trip.durationInMinutes.toString() + " min",
                    fontSize = 8.ssp,
                    color = colorResource(id = R.color.gray_600),
                )
            }

            Address(
                icon = painterResource(id = R.drawable.ic_destination),
                address = trip.destinationAddress.address
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithText(
                    icon = painterResource(id = R.drawable.ic_clock),
                    text = trip.minutesToDriverArrival.toString() + " min"
                )
                IconWithText(
                    icon = painterResource(id = R.drawable.ic_location),
                    text = trip.kilometersToDriverArrival.toString() + " km"
                )
                IconWithText(
                    icon = painterResource(id = R.drawable.ic_money),
                    text = trip.cost.toString() + "k"
                )
            }
        }
    }
}

@Composable
private fun Address(
    icon: Painter,
    address: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.sdp)
            .padding(bottom = 3.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(18.sdp),
            painter = icon,
            contentDescription = null
        )

        AppText(
            text = address,
            fontSize = 10.ssp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.sdp)
                .weight(1f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }

    // divider
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(id = R.color.divider_color))
    )
}

@Composable
private fun IconWithText(
    icon: Painter,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        Image(
            modifier = Modifier
                .size(15.sdp),
            painter = icon,
            contentDescription = null,
        )

        AppText(
            text = text,
            fontSize = 10.ssp,
            color = Color.Black,
        )
    }
}