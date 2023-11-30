package com.cuongnl.ridehailing.screens.home.bottombar

import com.cuongnl.ridehailing.R

sealed class BottomBarScreen(
    val route: String,
    val bottomBarTitleId: Int,
    val iconId: Int,
) {
    object Booking : BottomBarScreen(
        route = "booking",
        bottomBarTitleId = R.string.booking_text,
        iconId = R.drawable.ic_booking,
    )

    object History : BottomBarScreen(
        route = "history",
        bottomBarTitleId = R.string.activity_text,
        iconId = R.drawable.ic_history,
    )

    object Notification : BottomBarScreen(
        route = "notification",
        bottomBarTitleId = R.string.notification_text,
        iconId = R.drawable.ic_notification,
    )

    object Account : BottomBarScreen(
        route = "account",
        bottomBarTitleId = R.string.account_text,
        iconId = R.drawable.ic_account,
    )
}