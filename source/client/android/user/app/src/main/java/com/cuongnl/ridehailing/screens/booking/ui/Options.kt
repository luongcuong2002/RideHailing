package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun Options() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.sdp)
    ) {
        PaymentMethod()

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(20.sdp)
                .background(colorResource(id = R.color.orange_300))
        )

        NoteForDriver()
    }
}

@Composable
private fun RowScope.PaymentMethod(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {
    NoRippleButton(
        modifier = Modifier.weight(1f),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp, Alignment.CenterHorizontally)
        ) {
            Image(
                modifier = Modifier
                    .size(20.sdp),
                painter = painterResource(id = bookingActivityUiViewModel.paymentMethod.value.icon),
                contentDescription = null
            )
            AppText(
                modifier = Modifier
                    .padding(bottom = 2.sdp),
                text = stringResource(id = bookingActivityUiViewModel.paymentMethod.value.title),
                fontSize = 12.sp,
                color = Color.Black,
            )
        }
    }
}

@Composable
private fun RowScope.NoteForDriver(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val text = bookingActivityUiViewModel.noteForDriver.value.ifEmpty {
        stringResource(id = R.string.note_text)
    }

    NoRippleButton(
        modifier = Modifier.weight(1f),
        onClick = {
            bookingActivityUiViewModel.clickNoteForDriver(context)
        }
    ) {
        AppText(
            modifier = Modifier
                .align(Alignment.Center)
                .width(70.sdp),
            text = text,
            fontSize = 12.sp,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}