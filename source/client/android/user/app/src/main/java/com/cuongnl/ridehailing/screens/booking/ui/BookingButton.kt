package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BookingButton(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val context = LocalContext.current

    TouchableOpacityButton(
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .padding(horizontal = 15.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(colorResource(id = R.color.app_color))
            .padding(vertical = 10.sdp),
        onClick = {
            bookingActivityUiViewModel.clickBookingButton(context)
        }
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.book_text) + " " + bookingActivityUiViewModel.bookingsInfo[bookingActivityUiViewModel.selectedBookingIndex.value].transportationType.globalName,
            fontSize = 14.ssp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Medium
        )
    }
}