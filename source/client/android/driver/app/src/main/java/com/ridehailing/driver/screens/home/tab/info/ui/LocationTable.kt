package com.ridehailing.driver.screens.home.tab.info.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.viewmodel.InfoTabUiViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun LocationTable() {
    Column(
        modifier = Modifier
            .padding(top = 16.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(Color.White)
    ) {
        Header()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.sdp)
                .padding(bottom = 10.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowInfo(
                stringResource(id = R.string.address_text),
                CurrentLocation.getAddress()
            )
            RowInfo(
                stringResource(id = R.string.coordinate_text),
                CurrentLocation.getLatitude().toString() + ", " + CurrentLocation.getLongitude().toString()
            )

            ChangeButton()
        }
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.sdp)
            .background(colorResource(id = R.color.table_header_background))
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.location_text),
            color = Color.Black,
            fontSize = 8.ssp
        )
    }
}

@Composable
private fun ChangeButton(
    infoTabUiViewModel: InfoTabUiViewModel = viewModel()
) {
    TouchableOpacityButton(
        modifier = Modifier
            .padding(top = 10.sdp)
            .clip(RoundedCornerShape(20))
            .background(colorResource(id = R.color.app_color))
            .padding(vertical = 6.sdp)
            .padding(horizontal = 16.sdp),
        onClick = {
            infoTabUiViewModel.clickChangeLocation()
        }
    ) {
        AppText(
            modifier = Modifier
                .align(Alignment.Center),
            text = stringResource(id = R.string.change_text),
            color = Color.White,
            fontSize = 10.ssp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RowInfo(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .padding(top = 11.sdp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 3.sdp)
                .padding(horizontal = 5.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = title,
                color = Color.Black,
                fontSize = 10.ssp
            )
            AppText(
                text = content,
                color = colorResource(id = R.color.table_content_text_color),
                fontSize = 10.ssp
            )
        }
        Divider()
    }
}

@Composable
private fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(id = R.color.divider_color))
    )
}