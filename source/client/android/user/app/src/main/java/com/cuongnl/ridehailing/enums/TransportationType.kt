package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R
import com.google.maps.model.TravelMode

enum class TransportationType(
    val text: Int,
    val icon: Int,
    val bannerFareCalculationInfo: Int,
    val travelMode: TravelMode,
    val globalName: String,
    val characteristicText: Int
) {
    TAXI(
        R.string.taxi_text,
        R.drawable.ic_taxi,
        R.drawable.banner_taxi_fare_calcualtion_info,
        TravelMode.DRIVING,
        "Cam SM Taxi",
        R.string.taxi_characteristic_text,
    ),
    BIKE(
        R.string.bike_text,
        R.drawable.ic_bike,
        R.drawable.banner_bike_fare_calcualtion_info,
        TravelMode.BICYCLING,
        "Cam SM Bike",
        R.string.bike_characteristic_text,
    ),
}