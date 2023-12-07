package com.ridehailing.driver.screens.home.tab.trip.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ridehailing.driver.R
import com.ridehailing.driver.screens.home.LocalHomeViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ConfirmDialog() {

    val homeUiViewModel = LocalHomeViewModel.current

    val currentTrip = homeUiViewModel.selectedTrip.value
    
    if (currentTrip != null) {

        val context = LocalContext.current

        Dialog(
            onDismissRequest = {
                homeUiViewModel.setSelectedTrip(null)
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.sdp))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.gray_200))
                        .padding(10.sdp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(
                        modifier = Modifier
                            .border(1.dp, Color.Red)
                            .padding(vertical = 4.sdp, horizontal = 10.sdp),
                        text = "Cước ứng dụng",
                        fontSize = 11.ssp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    AppText(
                        modifier = Modifier
                            .background(colorResource(id = R.color.teal_700))
                            .padding(vertical = 4.sdp, horizontal = 10.sdp),
                        text = currentTrip.paymentMethod,
                        textAlign = TextAlign.Center,
                        fontSize = 11.ssp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                AppText(
                    text = stringResource(id = R.string.pick_up_location_text),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.ssp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.sdp),
                    textAlign = TextAlign.Center
                )

                AppText(
                    modifier = Modifier
                        .padding(top = 5.sdp)
                        .padding(horizontal = 10.sdp)
                        .fillMaxWidth(),
                    text = currentTrip.pickupAddress.address,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.ssp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )

                AppText(
                    text = currentTrip.distanceInKilometers.toString() + " km",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.ssp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 10.sdp)
                        .background(colorResource(id = R.color.gray_600))
                        .padding(vertical = 2.sdp, horizontal = 7.sdp),
                )

                AppText(
                    text = stringResource(id = R.string.drop_off_location_text),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.ssp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                AppText(
                    modifier = Modifier
                        .padding(top = 5.sdp)
                        .padding(horizontal = 10.sdp)
                        .fillMaxWidth(),
                    text = currentTrip.destinationAddress.address,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.ssp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )

                TouchableOpacityButton(
                    modifier = Modifier
                        .padding(top = 20.sdp)
                        .padding(bottom = 10.sdp)
                        .clip(RoundedCornerShape(5.sdp))
                        .background(colorResource(id = R.color.app_color))
                        .padding(vertical = 8.sdp)
                        .padding(horizontal = 20.sdp),
                    onClick = {
                        homeUiViewModel.clickToAcceptARide()
                    }
                ) {
                    AppText(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = stringResource(id = R.string.accept_a_ride),
                        fontSize = 12.ssp,
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}