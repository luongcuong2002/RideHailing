package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.ConfirmLocationState
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng

class ConfirmLocationViewModel : ViewModel() {

    private val _confirmLocationState =
        mutableStateOf(ConfirmLocationState.CHOOSING_DESTINATION_LOCATION)
    private val _destinationLocationLatLng = mutableStateOf<LatLng?>(null)
    private val _pickupLocationLatLng = mutableStateOf<LatLng?>(null)
    private val _destinationLocationAddress = mutableStateOf<String?>(null)
    private val _pickupLocationAddress = mutableStateOf<String?>(null)
    private val _isAddressLoading = mutableStateOf(false)

    val confirmLocationState: State<ConfirmLocationState> = _confirmLocationState
    val destinationLocationLatLng: State<LatLng?> = _destinationLocationLatLng
    val pickupLocationLatLng: State<LatLng?> = _pickupLocationLatLng
    val destinationLocationAddress: State<String?> = _destinationLocationAddress
    val pickupLocationAddress: State<String?> = _pickupLocationAddress
    val isAddressLoading: State<Boolean> = _isAddressLoading

    fun setConfirmLocationState(confirmLocationState: ConfirmLocationState) {
        _confirmLocationState.value = confirmLocationState
    }

    fun setDestinationLocationLatLngAndLoadAddress(context: Context, latLng: LatLng) {
        _destinationLocationLatLng.value = latLng
        loadAddress(context, latLng)
    }

    fun setPickupLocationLatLngAndLoadAddress(context: Context, latLng: LatLng) {
        _pickupLocationLatLng.value = latLng
        loadAddress(context, latLng)
    }

    private fun loadAddress(context: Context, latLng: LatLng) {

        setAddressLoading(true)

        MapUtils.getAddressByCoordinates(
            context,
            latLng,
            onSuccess = {

                when (confirmLocationState.value) {
                    ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {
                        _destinationLocationAddress.value = it
                    }

                    ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                        _pickupLocationAddress.value = it
                    }
                }

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
}