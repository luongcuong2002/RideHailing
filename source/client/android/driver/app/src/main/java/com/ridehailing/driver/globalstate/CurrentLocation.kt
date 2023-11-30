package com.ridehailing.driver.globalstate

object CurrentLocation {

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var address: String = ""

    fun getLatitude(): Double {
        return latitude
    }

    fun setLatitude(latitude: Double) {
        this.latitude = latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude = longitude
    }

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }
}