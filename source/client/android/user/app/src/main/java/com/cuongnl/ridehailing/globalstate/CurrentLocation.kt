package com.cuongnl.ridehailing.globalstate

import android.content.Context
import android.location.Location
import android.widget.Toast
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng

object CurrentLocation {

    private lateinit var location: Location
    private lateinit var fullAddress: String

    fun init(context: Context) {
        MapUtils.getCurrentLocation(
            context,
            onSuccess = {
                setLocation(it)
                fetchFullAddress(context)
            },
            onFailure = {
                Toast.makeText(context, "Cannot get current location", Toast.LENGTH_SHORT).show()
            },
            onPermissionNotGranted = {
                Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setLocation(location: Location) {
        this.location = location
    }

    private fun fetchFullAddress(context: Context) {

        MapUtils.getAddressByCoordinates(
            context,
            getLatLng(),
            onSuccess = {
                fullAddress = it
            },
            onFailure = {
                Toast.makeText(context, "Cannot get current address", Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun getLocation(): Location {
        return location
    }

    fun getLatLng(): LatLng {
        return LatLng(location.latitude, location.longitude)
    }

    fun getFullAddress(): String {
        return fullAddress
    }
}