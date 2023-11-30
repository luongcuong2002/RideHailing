package com.cuongnl.ridehailing.screens.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.screens.home.bottombar.BottomBar
import com.cuongnl.ridehailing.screens.home.bottombar.BottomNavGraph
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CurrentLocation.init(this)

        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {

    val navController = rememberNavController()

    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(0.30f))
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