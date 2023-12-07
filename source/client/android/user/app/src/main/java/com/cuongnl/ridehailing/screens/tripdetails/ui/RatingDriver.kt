package com.cuongnl.ridehailing.screens.tripdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.TripDetailsUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun RatingDriver(
    tripDetailsUiViewModel: TripDetailsUiViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .padding(top = 10.sdp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 30.sdp)
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = stringResource(id = R.string.rating_driver),
                fontSize = 14.ssp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 35.sdp)
            )

            AppText(
                text = stringResource(id = R.string.how_do_you_rate_your_driver),
                fontSize = 10.ssp,
                modifier = Modifier
                    .padding(top = 10.sdp),
                textAlign = TextAlign.Center
            )
            RatingBar()
        }

        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(60.sdp)
                .clip(CircleShape)
                .border(
                    BorderStroke(3.sdp, colorResource(id = R.color.gray_300)),
                    CircleShape
                )
        )
    }
}

@Composable
private fun RatingBar(
    tripDetailsUiViewModel: TripDetailsUiViewModel = viewModel()
) {

    Row(
        modifier = Modifier
            .padding(vertical = 15.sdp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(5) {

            val icon = if ((it + 1) <= tripDetailsUiViewModel.starNumber.value)
                    painterResource(id = R.drawable.ic_active_star)
                else
                    painterResource(id = R.drawable.ic_inactive_star)

            NoRippleButton(
                onClick = {
                    if (!tripDetailsUiViewModel.isRated.value) {
                        tripDetailsUiViewModel.setStarNumber(it + 1)
                    }
                }
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.sdp)
                )
            }
        }
    }
}