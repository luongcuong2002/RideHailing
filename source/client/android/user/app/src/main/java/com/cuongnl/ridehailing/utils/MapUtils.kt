package com.cuongnl.ridehailing.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import com.cuongnl.ridehailing.BuildConfig
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode
import java.io.IOException
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


object MapUtils {

    const val TAG = "MapUtils"

    fun getAddressByCoordinates(
        context: Context,
        latLng: LatLng,
        onSuccess: (String) -> Unit = {},
        onFailure: () -> Unit = {}
    ) {

        if (!Constant.ENABLE_CALL_MAP_API) {
            Handler().postDelayed({
                onSuccess("This is a test address")
            }, 1000)
            return
        }

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

        onSuccess(
            Location("").apply {
                latitude = 20.9808164
                longitude = 105.7936536
            }
        )
        return
//        if (!Constant.ENABLE_CALL_MAP_API) {
//            onSuccess(
//                Location("").apply {
//                    latitude = 20.9808164
//                    longitude = 105.7936536
//                }
//            )
//            return
//        }

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

        if (!Constant.ENABLE_CALL_MAP_API) {
            onSuccess(
                Place.builder()
                    .setLatLng(LatLng(20.9808164, 105.7936536))
                    .setName("Test place")
                    .build()
            )
            return
        }

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

    fun getDirectionsBetweenTwoPoints(
        originLatLng: LatLng,
        destinationLatLng: LatLng,
        travelMode: TravelMode,
        callBack: com.google.maps.PendingResult.Callback<DirectionsResult>
    ) {

        if (!Constant.ENABLE_CALL_MAP_API) {
            callBack.onResult(null)
            return
        }

        val newOriginLatLng =
            com.google.maps.model.LatLng(originLatLng.latitude, originLatLng.longitude)
        val newDestinationLatLng =
            com.google.maps.model.LatLng(destinationLatLng.latitude, destinationLatLng.longitude)

        val geoApiContext = GeoApiContext.Builder()
            .apiKey(BuildConfig.MAPS_API_KEY)
            .build()

        val request = DirectionsApi.newRequest(geoApiContext)
            .mode(travelMode)
            .origin(newOriginLatLng)
            .destination(newDestinationLatLng)

        request.setCallback(callBack)
    }

    fun getZoomLevelFitMap(point1: LatLng, point2: LatLng): Float {
        val distance = calculateDistance(point1, point2)
        val radius = distance / 2
        val scale = ((radius * 1000) / 500)
        return ((16 - ln(scale) / ln(2.0)).toFloat())
    }

    fun calculateDistance(point1: LatLng, point2: LatLng): Double {
        val earthRadius = 6371 // Radius of the Earth in kilometers

        val lat1 = Math.toRadians(point1.latitude)
        val lon1 = Math.toRadians(point1.longitude)
        val lat2 = Math.toRadians(point2.latitude)
        val lon2 = Math.toRadians(point2.longitude)

        val dlon = lon2 - lon1
        val dlat = lat2 - lat1

        val a = sin(dlat / 2).pow(2) + cos(lat1) * cos(lat2) * sin(dlon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }
}