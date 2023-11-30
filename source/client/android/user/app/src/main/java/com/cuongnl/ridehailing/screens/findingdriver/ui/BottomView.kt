package com.cuongnl.ridehailing.screens.findingdriver.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.FindingDriverViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BoxScope.BottomView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(horizontal = 10.sdp, vertical = 20.sdp)
    ) {
        FindingDriver()
    }
}

@Composable
private fun FindingDriver(
    findingDriverViewModel: FindingDriverViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                10.dp,
                RoundedCornerShape(20)
            )
            .clip(RoundedCornerShape(20))
            .background(Color.White)
            .padding(horizontal = 15.sdp, vertical = 5.sdp)
    ) {
        Image(
            painter = painterResource(id = findingDriverViewModel.getTransportationType().icon),
            contentDescription = null,
            modifier = Modifier
                .size(35.sdp)
        )

        AppText(
            text = stringResource(id = R.string.finding_a_car),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 15.sdp)
                .align(Alignment.CenterVertically),
        )

        val dotText = when (findingDriverViewModel.dotCount.value) {
            0 -> "."
            1 -> ".."
            2 -> "..."
            else -> ""
        }

        AppText(
            text = dotText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}