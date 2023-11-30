package com.cuongnl.ridehailing.screens.home.tab.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.home.tab.booking.LocalBehavior
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun WhereDoYouWantToGo() {

    val actions = LocalBehavior.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.sdp)
            .clip(RoundedCornerShape(25))
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures {

                }
            }
            .padding(start = 15.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.routes_flag),
            contentDescription = null,
            modifier = Modifier
                .size(20.sdp)
        )
        AppText(
            text = stringResource(id = R.string.where_to_text),
            color = colorResource(id = R.color.gray_900),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.sdp)
        )

        NoRippleButton(
            onClick = {
                actions.navigateToConfirmLocationScreen()
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 8.sdp)
                    .height(28.sdp)
                    .clip(RoundedCornerShape(50))
                    .background(colorResource(id = R.color.gray_200))
                    .padding(horizontal = 10.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp, Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pin_location),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.sdp)
                )
                AppText(
                    text = stringResource(id = R.string.map_text),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.gray_900),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}