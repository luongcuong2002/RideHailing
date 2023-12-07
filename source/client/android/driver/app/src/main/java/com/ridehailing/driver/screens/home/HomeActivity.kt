package com.ridehailing.driver.screens.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.globalstate.CurrentDriver
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.models.api.DriverConnectToSocket
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.screens.home.bottombar.BottomBar
import com.ridehailing.driver.screens.home.bottombar.BottomNavGraph
import com.ridehailing.driver.theme.AppTheme
import com.ridehailing.driver.viewmodel.HomeUiViewModel

val LocalHomeViewModel = staticCompositionLocalOf<HomeUiViewModel> {
    error("No LocalHomeViewModel provided")
}

class HomeActivity : BaseActivity() {

    private lateinit var homeUiViewModel: HomeUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeUiViewModel = ViewModelProvider(this)[HomeUiViewModel::class.java]

        setContent {
            CompositionLocalProvider(value = LocalHomeViewModel provides homeUiViewModel) {
                Screen()
            }
        }

        CurrentLocation.fetchAddress(application)

        BookingSocket.connect(this)
        BookingSocket.emitDriverConnectToSocket(
            DriverConnectToSocket(
                CurrentDriver.getDriver().phoneNumber.value,
                CurrentLocation.latLng.value.latitude,
                CurrentLocation.latLng.value.longitude,
            ).toJson()
        )
        homeUiViewModel.setupListeners(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        BookingSocket.disconnect()
    }
}

@Composable
private fun Screen() {
    val navController = rememberNavController()

    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            BottomNavGraph(
                navController,
                modifier = Modifier
                    .weight(1f)
            )

            BottomBar(navController = navController)
        }
    }
}