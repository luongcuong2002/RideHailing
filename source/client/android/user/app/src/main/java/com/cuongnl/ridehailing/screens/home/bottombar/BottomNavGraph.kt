package com.cuongnl.ridehailing.screens.home.bottombar

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cuongnl.ridehailing.screens.home.tab.account.AccountTab
import com.cuongnl.ridehailing.screens.home.tab.booking.BookingTab
import com.cuongnl.ridehailing.screens.home.tab.history.HistoryTab
import com.cuongnl.ridehailing.screens.home.tab.notification.NotificationTab
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Booking.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Booking.route) {
            BookingTab()
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryTab()
        }
        composable(route = BottomBarScreen.Notification.route) {
            NotificationTab()
        }
        composable(route = BottomBarScreen.Account.route) {
            AccountTab()
        }
    }
}