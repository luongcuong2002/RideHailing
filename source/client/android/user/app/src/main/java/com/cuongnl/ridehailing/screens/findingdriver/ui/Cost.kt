package com.cuongnl.ridehailing.screens.findingdriver.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.FindingDriverViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun Cost(
    findingDriverViewModel: FindingDriverViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .padding(start = 20.sdp)
            .shadow(
                10.dp,
                RoundedCornerShape(10)
            )
            .clip(RoundedCornerShape(10))
            .background(Color.White)
            .padding(horizontal = 10.sdp, vertical = 5.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = findingDriverViewModel.getPaymentMethod().icon),
                contentDescription = null,
                modifier = Modifier
                    .size(20.sdp)
            )

            AppText(
                text = stringResource(id = findingDriverViewModel.getPaymentMethod().title),
                fontSize = 11.ssp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.sdp, bottom = 3.dp)
            )
        }

        AppText(
            text = findingDriverViewModel.getCost().toString() + "k",
            fontSize = 11.ssp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 50.sdp, bottom = 3.dp)
                .align(Alignment.CenterVertically),
        )
    }
}