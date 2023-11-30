package com.ridehailing.driver.screens.home.bottombar

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ridehailing.driver.screens.home.tab.info.InfoTab
import com.ridehailing.driver.screens.home.tab.trip.TripTab

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Info.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Info.route) {
            InfoTab()
        }
        composable(route = BottomBarScreen.Trip.route) {
            TripTab()
        }
    }
}