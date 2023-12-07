package com.ridehailing.driver.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.ridehailing.driver.extensions.findActivity
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.utils.MapUtils

class ConfirmLocationViewModel : ViewModel() {

    private val _locationLatLng = mutableStateOf(CurrentLocation.latLng.value)
    private val _locationAddress = mutableStateOf(CurrentLocation.address.value)
    private val _isAddressLoading = mutableStateOf(false)

    val locationLatLng: State<LatLng> = _locationLatLng
    val locationAddress: State<String> = _locationAddress
    val isAddressLoading: State<Boolean> = _isAddressLoading

    fun setLocationLatLngAndLoadAddress(context: Context, latLng: LatLng) {
        _locationLatLng.value = latLng
        loadAddress(context, latLng)
    }

    private fun loadAddress(context: Context, latLng: LatLng) {

        setAddressLoading(true)

        MapUtils.getAddressByCoordinates(
            context,
            latLng,
            onSuccess = {
                _locationAddress.value = it
                setAddressLoading(false)
            },
            onFailure = {
                setAddressLoading(false)
            }
        )
    }

    private fun setAddressLoading(isLoading: Boolean) {
        _isAddressLoading.value = isLoading
    }

    fun clickConfirmButton(context: Context) {
        CurrentLocation.setLatLngAndUpdateToServer(locationLatLng.value)
        CurrentLocation.setAddress(locationAddress.value)
        context.findActivity()?.finish()
    }

    fun clickBackButton(context: Context) {
        context.findActivity()?.finish()
    }
}