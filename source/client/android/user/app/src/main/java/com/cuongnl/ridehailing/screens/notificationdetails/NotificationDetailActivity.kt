package com.cuongnl.ridehailing.screens.notificationdetails

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.notificationdetails.ui.AppBar
import com.cuongnl.ridehailing.screens.notificationdetails.ui.BannerImage
import com.cuongnl.ridehailing.screens.notificationdetails.ui.TextContent
import com.cuongnl.ridehailing.theme.AppTheme

class NotificationDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {

    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            AppBar()
            BannerImage()
            TextContent()
        }
    }
}