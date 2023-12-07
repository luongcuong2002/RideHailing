package com.cuongnl.ridehailing.screens.booking

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.screens.booking.ui.BackButton
import com.cuongnl.ridehailing.screens.booking.ui.BottomView
import com.cuongnl.ridehailing.screens.booking.ui.FareCalculationInfoBottomSheet
import com.cuongnl.ridehailing.screens.booking.ui.MapView
import com.cuongnl.ridehailing.screens.home.HomeActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import ir.kaaveh.sdpcompose.sdp

class BookingActivity : BaseActivity() {

    private lateinit var bookingActivityUiViewModel: BookingActivityUiViewModel
    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        bookingActivityUiViewModel = ViewModelProvider(this)[BookingActivityUiViewModel::class.java]

        val destinationLocationLatLng =
            intent.getParcelableExtra<LatLng>(Constant.BUNDLE_DESTINATION_LAT_LNG)
        val destinationLocationAddress = intent.getStringExtra(Constant.BUNDLE_DESTINATION_ADDRESS)
        val pickupLocationLatLng = intent.getParcelableExtra<LatLng>(Constant.BUNDLE_PICKUP_LAT_LNG)
        val pickupLocationAddress = intent.getStringExtra(Constant.BUNDLE_PICKUP_ADDRESS)
        val transportationType = intent.getSerializableExtra(Constant.BUNDLE_TRAVEL_MODE) as TransportationType?

        bookingActivityUiViewModel.setDestinationLocationLatLng(destinationLocationLatLng!!)
        bookingActivityUiViewModel.setDestinationLocationAddress(destinationLocationAddress!!)
        bookingActivityUiViewModel.setPickupLocationLatLng(pickupLocationLatLng!!)
        bookingActivityUiViewModel.setPickupLocationAddress(pickupLocationAddress!!)

        if (transportationType != null) {
            bookingActivityUiViewModel.selectBookingInfo(transportationType)
        } else {
            bookingActivityUiViewModel.selectBookingInfo(TransportationType.TAXI)
        }

        bookingActivityUiViewModel.getData(this)

        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]

        val zoomLevelFitMap = MapUtils.getZoomLevelFitMap(pickupLocationLatLng, destinationLocationLatLng)
        val destinationZoomLevel = zoomLevelFitMap - 1.2f
        mapViewModel.setCameraPositionState(CameraPositionState(
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    (pickupLocationLatLng.latitude + destinationLocationLatLng.latitude) / 2,
                    (pickupLocationLatLng.longitude + destinationLocationLatLng.longitude) / 2,
                ),
                destinationZoomLevel
            )
        ))
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constant.REQUEST_CODE_NOTE_FOR_DRIVER && resultCode == RESULT_OK && data != null) {
            val noteForDriver = data.getStringExtra(Constant.BUNDLE_NOTE_FOR_DRIVER)
            noteForDriver?.let {
                bookingActivityUiViewModel.setNoteForDriver(it)
            }
        }
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                MapView()

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(10.sdp)
                        .clip(RoundedCornerShape(topStart = 20.sdp, topEnd = 20.sdp))
                        .background(Color.White)
                )

                BackButton()
            }
            BottomView()
        }

        FareCalculationInfoBottomSheet()
    }
}