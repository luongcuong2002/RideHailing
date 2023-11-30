package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.CountryCode

class CountryCodeSelectedViewModel : ViewModel() {

    private val _currentCountryCode = mutableStateOf(CountryCode.VIETNAM)

    val currentCountryCode: State<CountryCode> = _currentCountryCode

    fun setCurrentCountryCode(countryCode: CountryCode) {
        _currentCountryCode.value = countryCode
    }
}