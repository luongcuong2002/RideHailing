package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class BookingTabUiViewModel(application: Application) : AndroidViewModel(application) {

    private var _isLoadingAddress = mutableStateOf(true)

    val isLoadingAddress: State<Boolean> = _isLoadingAddress

    fun setIsLoadingAddress(isLoadingAddress: Boolean) {
        _isLoadingAddress.value = isLoadingAddress
    }
}