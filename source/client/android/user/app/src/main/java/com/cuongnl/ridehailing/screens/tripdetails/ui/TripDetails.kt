package com.cuongnl.ridehailing.screens.tripdetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.PaymentMethod
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.TripDetailsUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun TripDetails(
    tripDetailsUiViewModel: TripDetailsUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = 10.sdp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.sdp)
    ) {
        AppText(
            text = stringResource(id = R.string.charge_by_cash),
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.gray_700),
            fontSize = 11.ssp,
        )

        Row(
            modifier = Modifier
                .padding(top = 2.sdp, bottom = 10.sdp),
            horizontalArrangement = Arrangement.spacedBy(7.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = (tripDetailsUiViewModel.getCost() * 1000).toString() + "đ",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 14.ssp,
            )

            Image(
                painter = painterResource(id = PaymentMethod.CASH.icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.sdp)
                    .size(20.sdp)
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 5.sdp, end = 10.sdp),
            horizontalArrangement = Arrangement.spacedBy(10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pickup),
                contentDescription = null,
                modifier = Modifier
                    .size(25.sdp)
            )
            AppText(
                text = tripDetailsUiViewModel.getDriverAcceptResponse().pickupAddress,
                fontSize = 13.ssp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 10.sdp, bottom = 10.sdp)
                .padding(start = 5.sdp, end = 10.sdp),
            horizontalArrangement = Arrangement.spacedBy(10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_destination),
                contentDescription = null,
                modifier = Modifier
                    .size(25.sdp)
            )
            AppText(
                text = tripDetailsUiViewModel.getDriverAcceptResponse().destinationAddress,
                fontSize = 13.ssp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        AppText(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                ) {
                    append(stringResource(id = R.string.time_text) + ": ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.ssp,
                        color = colorResource(id = R.color.gray_700),
                    )
                ) {
                    append(FormatterUtils.convertTimestampToString(System.currentTimeMillis()))
                }
            },
        )
    }
}