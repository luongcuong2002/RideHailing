package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.CountryCode
import com.cuongnl.ridehailing.viewmodel.CountryCodeBottomSheetViewModel
import com.cuongnl.ridehailing.viewmodel.CountryCodeSelectedViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.MovableView
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CountryCodeList(countryCodeBottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        content = {
            items(countryCodeBottomSheetViewModel.phoneCountryCodes) {
                CountryCodeItem(it)
            }
        }
    )
}

@Composable
private fun CountryCodeItem(
    item: CountryCode,
    countryCodeSelectedViewModel: CountryCodeSelectedViewModel = viewModel(),
    countryCodeBottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 15.sdp)
            .clickable {
                countryCodeSelectedViewModel.setCurrentCountryCode(item)
                countryCodeBottomSheetViewModel.setBottomSheetVisible(false)
            }
    ) {
        CountryFlag(item.countryFlag)
        CountryName(item.countryName)
        CountryCode(item.phoneCode)
    }
}

@Composable
private fun CountryFlag(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier
            .width(30.sdp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.CountryName(name: String) {
    MovableView(
        modifier = Modifier
            .weight(1f)
            .padding(start = 10.sdp, end = 20.sdp)
    ) {
        AppText(
            text = name,
            fontSize = 15.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun CountryCode(code: String) {
    AppText(
        text = code,
        fontSize = 16.sp,
        color = colorResource(id = R.color.black),
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Bold,
    )
}