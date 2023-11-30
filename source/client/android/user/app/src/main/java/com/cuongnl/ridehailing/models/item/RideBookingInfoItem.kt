package com.cuongnl.ridehailing.models.item

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.models.api.GetBookingInfoResponse

data class RideBookingInfoItem(
    val transportationType: TransportationType,
    var bookingInfoResponse: GetBookingInfoResponse? = null,
    var directionPoints: List<com.google.android.gms.maps.model.LatLng>? = null,
    var travelTimeInMinutes: Int? = null,
    var distanceInKilometers: Double? = null,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)
