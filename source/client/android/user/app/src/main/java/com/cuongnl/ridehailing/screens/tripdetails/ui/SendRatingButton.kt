package com.cuongnl.ridehailing.screens.tripdetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.TripDetailsUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SendRatingButton(
    tripDetailsUiViewModel: TripDetailsUiViewModel = viewModel()
) {
    if (tripDetailsUiViewModel.starNumber.value > 0 && !tripDetailsUiViewModel.isRated.value) {

        val context = LocalContext.current

        TouchableOpacityButton(
            modifier = Modifier
                .padding(top = 10.sdp)
                .clip(RoundedCornerShape(15))
                .background(color = colorResource(id = R.color.orange_500))
                .padding(horizontal = 10.sdp, vertical = 7.sdp),
            onClick = {
                if (!tripDetailsUiViewModel.isSendingRating.value) {
                    tripDetailsUiViewModel.onRatingDriver(context)
                }
            },
        ) {
            AppText(
                text = stringResource(id = R.string.send_rating),
                fontSize = 11.ssp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}