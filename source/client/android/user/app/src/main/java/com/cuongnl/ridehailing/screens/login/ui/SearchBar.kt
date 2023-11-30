package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.CountryCodeBottomSheetViewModel
import com.cuongnl.ridehailing.widgets.TextFieldWithBorder
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SearchBar(countryCodeBottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()) {
    TextFieldWithBorder(
        onValueChange = {
            countryCodeBottomSheetViewModel.filterCountryCodeList(it.text)
        },
        modifier = Modifier
            .padding(bottom = 20.sdp),
        placeholder = stringResource(id = R.string.search_text),
    )
}