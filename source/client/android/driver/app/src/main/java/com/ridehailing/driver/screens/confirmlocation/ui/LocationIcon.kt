package com.ridehailing.driver.screens.confirmlocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ridehailing.driver.R

@Composable
fun LocationIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_placeholder),
        contentDescription = null,
        modifier = Modifier
            .size(20.dp)
    )
}