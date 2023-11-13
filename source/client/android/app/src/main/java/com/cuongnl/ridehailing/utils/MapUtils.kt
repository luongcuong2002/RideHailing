package com.cuongnl.ridehailing.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import java.io.IOException

object MapUtils {

    const val TAG = "MapUtils"

    fun getAddressByCoordinates(
        context: Context,
        latLng: LatLng,
        onSuccess: (String) -> Unit = {},
        onFailure: () -> Unit = {}
    ) {

        val geocoder = Geocoder(context)

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1,
                    object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: MutableList<Address>) {
                            if (addresses.isNotEmpty()) {
                                onSuccess(addresses[0].getAddressLine(0))
                            }
                        }

                        override fun onError(errorMessage: String?) {
                            onFailure()
                        }
                    })
            } else {
                val addresses: MutableList<Address>? =
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

                if (addresses.isNullOrEmpty()) {
                    onFailure()
                } else {
                    onSuccess(addresses[0].getAddressLine(0))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getCurrentLocation(
        context: Context,
        onSuccess: (Location) -> Unit = {},
        onFailure: () -> Unit = {},
        onPermissionNotGranted: () -> Unit = {}
    ) {

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val task = if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            onPermissionNotGranted()
            return
        } else
            fusedLocationClient.lastLocation

        task.addOnCompleteListener {
            if (!it.isSuccessful || it.result == null) {
                onFailure()
            } else {
                onSuccess(it.result)
            }
        }
    }

    fun getPlaceById(
        placesClient: PlacesClient,
        placeId: String,
        placeFields: List<Place.Field>,
        onSuccess: (Place) -> Unit = {},
        onFailure: (Exception) -> Unit = {},
    ) {
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener { response: FetchPlaceResponse ->
                onSuccess(response.place)
            }.addOnFailureListener { exception: Exception ->
                if (exception is ApiException) {
                    Log.e(TAG, "Place not found: ${exception.message}")
                    val statusCode = exception.statusCode
                }
                onFailure(exception)
            }
    }
}