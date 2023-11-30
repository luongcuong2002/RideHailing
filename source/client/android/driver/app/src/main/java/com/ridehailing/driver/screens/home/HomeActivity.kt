package com.ridehailing.driver.screens.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.screens.home.bottombar.BottomBar
import com.ridehailing.driver.screens.home.bottombar.BottomNavGraph
import com.ridehailing.driver.theme.AppTheme

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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