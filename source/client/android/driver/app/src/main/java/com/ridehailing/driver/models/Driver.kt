package com.ridehailing.driver.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class Driver {

    private var _phoneNumber = mutableStateOf("null")
    private var _driverName = mutableStateOf("null")
    private var _driverAvatar = mutableStateOf<String?>(null)
    private var _licensePlate = mutableStateOf("null")
    private var _vehicleBrand = mutableStateOf("null")
    private var _travelMode = mutableStateOf("null")

    val phoneNumber : State<String> = _phoneNumber
    val driverName : State<String> = _driverName
    val driverAvatar : State<String?> = _driverAvatar
    val licensePlate : State<String> = _licensePlate
    val vehicleBrand : State<String> = _vehicleBrand
    val travelMode : State<String> = _travelMode

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun setDriverName(driverName: String) {
        _driverName.value = driverName
    }

    fun setDriverAvatar(driverAvatar: String?) {
        _driverAvatar.value = driverAvatar
    }

    fun setLicensePlate(licensePlate: String) {
        _licensePlate.value = licensePlate
    }

    fun setVehicleBrand(vehicleBrand: String) {
        _vehicleBrand.value = vehicleBrand
    }

    fun setTravelMode(travelMode: String) {
        _travelMode.value = travelMode
    }
}
