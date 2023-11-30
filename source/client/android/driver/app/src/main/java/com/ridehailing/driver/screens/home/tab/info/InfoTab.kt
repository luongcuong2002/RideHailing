package com.ridehailing.driver.screens.home.tab.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ridehailing.driver.screens.home.tab.info.ui.Avatar
import com.ridehailing.driver.screens.home.tab.info.ui.InfoTable
import com.ridehailing.driver.screens.home.tab.info.ui.LocationTable
import ir.kaaveh.sdpcompose.sdp

@Composable
fun InfoTab() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(horizontal = 10.sdp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar()
        InfoTable()
        LocationTable()
    }
}