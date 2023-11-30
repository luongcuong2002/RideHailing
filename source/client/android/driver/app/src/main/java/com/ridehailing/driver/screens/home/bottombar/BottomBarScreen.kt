package com.ridehailing.driver.screens.home.bottombar

import com.ridehailing.driver.R

sealed class BottomBarScreen(
    val route: String,
    val bottomBarTitleId: Int,
    val iconId: Int,
) {
    object Info : BottomBarScreen(
        route = "info",
        bottomBarTitleId = R.string.info_text,
        iconId = R.drawable.ic_home,
    )

    object Trip : BottomBarScreen(
        route = "trip",
        bottomBarTitleId = R.string.trip_text,
        iconId = R.drawable.ic_shopper,
    )
}