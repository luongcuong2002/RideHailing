package com.cuongnl.ridehailing.screens.findingdriver.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.RippleLoadingAnimation

@Composable
fun RadarScanner(
    modifier: Modifier = Modifier,
) {
    RippleLoadingAnimation(
        modifier = modifier,
        size = LocalConfiguration.current.screenWidthDp.dp,
        circleColor = colorResource(id = R.color.orange_200),
        numberOfCircles = 4,
        animationDelay = 4000,
    )
}