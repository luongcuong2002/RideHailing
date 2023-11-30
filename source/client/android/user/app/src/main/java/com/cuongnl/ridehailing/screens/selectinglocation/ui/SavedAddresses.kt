package com.cuongnl.ridehailing.screens.selectinglocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.viewmodel.SelectingLocationUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import com.google.android.gms.maps.model.LatLng
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SavedAddresses(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 10.sdp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            
            AppText(
                text = stringResource(id = R.string.saved),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            TouchableOpacityButton(
                onClick = {

                }
            ) {
                AppText(
                    text = stringResource(id = R.string.show_all),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.app_color)
                )
            }
        }

        AddressesList()
    }
}

@Composable
fun AddressesList() {

    val data = CurrentUser.getUser()!!.addresses

    LazyRow(
        modifier = Modifier
            .padding(top = 9.sdp),
        content = {
            items(data.size) { index ->
                AddressItem(data[index])
            }
        },
        horizontalArrangement = Arrangement.spacedBy(8.sdp, Alignment.CenterHorizontally),
    )
}

@Composable
private fun AddressItem(
    item: Address,
    selectingLocationUiViewModel: SelectingLocationUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val icon = when (item.addressType) {
        AddressType.HOME -> painterResource(id = R.drawable.ic_homeplace)
        AddressType.WORK -> painterResource(id = R.drawable.ic_workplace)
        else -> painterResource(id = R.drawable.ic_placeadd)
    }

    val title = when (item.addressType) {
        AddressType.HOME -> stringResource(id = R.string.home_text)
        AddressType.WORK -> stringResource(id = R.string.company_text)
        else -> item.fullName
    }

    val description = when (item.addressType) {
        AddressType.HOME -> item.fullName
        AddressType.WORK -> item.fullName
        else -> null
    }

    ItemContent(icon, title, description) {
        selectingLocationUiViewModel.onClickSavedAddress(context, item)
    }
}

@Composable
private fun ItemContent(
    icon: Painter? = null,
    title: String? = null,
    description: String? = null,
    onClick: () -> Unit = {},
) {

    NoRippleButton(
        onClick = {
            onClick()
        },
    ) {
        Row(
            modifier = Modifier
                .width(119.sdp)
                .height(32.sdp)
                .clip(RoundedCornerShape(30))
                .background(Color.White)
                .padding(horizontal = 5.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.sdp)
        ) {

            Image(
                painter = icon!!,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 5.sdp)
                    .size(20.sdp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {

                AppText(
                    text = title!!,
                    color = Color.Black,
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (description != null) {
                    AppText(
                        text = description,
                        color = Color.Black,
                        fontSize = 8.ssp,
                        fontWeight = FontWeight.Light,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 0.1.sp
                    )
                }
            }
        }
    }
}