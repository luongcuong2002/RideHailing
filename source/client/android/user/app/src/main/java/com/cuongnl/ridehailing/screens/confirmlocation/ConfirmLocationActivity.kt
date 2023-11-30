package com.cuongnl.ridehailing.screens.confirmlocation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.IConfirmDestinationLocationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.ConfirmLocationState
import com.cuongnl.ridehailing.enums.SelectingLocationType
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.screens.booking.BookingActivity
import com.cuongnl.ridehailing.screens.confirmlocation.ui.BackButton
import com.cuongnl.ridehailing.screens.confirmlocation.ui.BottomView
import com.cuongnl.ridehailing.screens.confirmlocation.ui.MapView
import com.cuongnl.ridehailing.screens.selectinglocation.SelectingLocationActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.ConfirmLocationViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader

val LocalActivityBehavior = androidx.compose.runtime.staticCompositionLocalOf<IConfirmDestinationLocationActivityBehavior> {
    error("No ActivityBehavior provided")
}

class ConfirmLocationActivity : BaseActivity(), IConfirmDestinationLocationActivityBehavior {

    private lateinit var confirmLocationViewModel: ConfirmLocationViewModel
    private lateinit var loaderViewModel: LoaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    private fun setupViewModel() {

        confirmLocationViewModel = ViewModelProvider(this)[ConfirmLocationViewModel::class.java]
        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]

        confirmLocationViewModel.setDestinationLocationLatLngAndLoadAddress(this, CurrentLocation.getLatLng())
    }

    override fun editLocation() {
        val intent = Intent(this, SelectingLocationActivity::class.java)

        if (confirmLocationViewModel.destinationLocationLatLng.value != null && confirmLocationViewModel.destinationLocationAddress.value != null) {
            intent.putExtra(Constant.BUNDLE_DESTINATION_LAT_LNG, confirmLocationViewModel.destinationLocationLatLng.value)
            intent.putExtra(Constant.BUNDLE_DESTINATION_ADDRESS, confirmLocationViewModel.destinationLocationAddress.value)
        }
        if (confirmLocationViewModel.pickupLocationLatLng.value != null && confirmLocationViewModel.pickupLocationAddress.value != null) {
            intent.putExtra(Constant.BUNDLE_PICKUP_LAT_LNG, confirmLocationViewModel.pickupLocationLatLng.value)
            intent.putExtra(Constant.BUNDLE_PICKUP_ADDRESS, confirmLocationViewModel.pickupLocationAddress.value)
        }
        when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> intent.putExtra(Constant.BUNDLE_SELECTING_LOCATION_TYPE, SelectingLocationType.DESTINATION_LOCATION)
            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> intent.putExtra(Constant.BUNDLE_SELECTING_LOCATION_TYPE, SelectingLocationType.PICKUP_LOCATION)
        }

        startActivity(intent)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onClickConfirmButton() {
        when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {

                confirmLocationViewModel.setConfirmLocationState(ConfirmLocationState.CHOOSING_PICKUP_LOCATION)

                if (confirmLocationViewModel.pickupLocationLatLng.value == null) {
                    confirmLocationViewModel.setPickupLocationLatLngAndLoadAddress(this, CurrentLocation.getLatLng())
                }
            }
            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                navigateToBookingActivity()
            }
        }
    }

    override fun onClickBackButton() {
        onBackPressed()
    }

    private fun navigateToBookingActivity() {
        val intent = Intent(this, BookingActivity::class.java)
        intent.putExtra(Constant.BUNDLE_DESTINATION_LAT_LNG, confirmLocationViewModel.destinationLocationLatLng.value)
        intent.putExtra(Constant.BUNDLE_PICKUP_LAT_LNG, confirmLocationViewModel.pickupLocationLatLng.value)
        intent.putExtra(Constant.BUNDLE_DESTINATION_ADDRESS, confirmLocationViewModel.destinationLocationAddress.value)
        intent.putExtra(Constant.BUNDLE_PICKUP_ADDRESS, confirmLocationViewModel.pickupLocationAddress.value)

        startActivity(intent)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onBackPressed() {

        when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                confirmLocationViewModel.setConfirmLocationState(ConfirmLocationState.CHOOSING_DESTINATION_LOCATION)
            }
        }
    }
}

@Composable
private fun Screen() {
    AppTheme {
        FullScreenLoader {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MapView()
                BottomView()
                BackButton()
            }
        }
    }
}