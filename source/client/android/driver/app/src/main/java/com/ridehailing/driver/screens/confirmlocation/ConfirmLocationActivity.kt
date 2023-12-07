package com.ridehailing.driver.screens.confirmlocation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.screens.confirmlocation.ui.BackButton
import com.ridehailing.driver.screens.confirmlocation.ui.BottomView
import com.ridehailing.driver.screens.confirmlocation.ui.MapView
import com.ridehailing.driver.theme.AppTheme

class ConfirmLocationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {

    }
}

@Composable
private fun Screen() {
    AppTheme {
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