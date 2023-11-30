package com.ridehailing.driver.models

class Driver {

    private var _phoneNumber : String = "null"
    private var _driverName : String = "null"
    private var _driverAvatar : String? = null
    private var _licensePlate : String = "null"
    private var _vehicleBrand : String = "null"
    private var _vehicleType : String = "null"

    val phoneNumber : String = _phoneNumber
    val driverName : String = _driverName
    val driverAvatar : String? = _driverAvatar
    val licensePlate : String = _licensePlate
    val vehicleBrand : String = _vehicleBrand
    val vehicleType : String = _vehicleType

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber = phoneNumber
    }

    fun setDriverName(driverName: String) {
        _driverName = driverName
    }

    fun setDriverAvatar(driverAvatar: String?) {
        _driverAvatar = driverAvatar
    }

    fun setLicensePlate(licensePlate: String) {
        _licensePlate = licensePlate
    }

    fun setVehicleBrand(vehicleBrand: String) {
        _vehicleBrand = vehicleBrand
    }

    fun setVehicleType(vehicleType: String) {
        _vehicleType = vehicleType
    }
}
