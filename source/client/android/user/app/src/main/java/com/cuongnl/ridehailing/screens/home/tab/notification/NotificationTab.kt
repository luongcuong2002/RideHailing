package com.cuongnl.ridehailing.screens.home.tab.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.screens.home.tab.notification.behavior.NotificationTabBehavior
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.DeleteButton
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.NotificationAppBar
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.NotificationsList
import com.cuongnl.ridehailing.viewmodel.NotificationTabUiViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.NotificationServiceViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.UserServiceViewModel

val LocalBehavior =
    staticCompositionLocalOf<NotificationTabBehavior> { error("No LocalActivityActionsClass provided") }

@Composable
fun NotificationTab(
    notificationServiceViewModel: NotificationServiceViewModel = viewModel(),
    notificationTabUiViewModel: NotificationTabUiViewModel = viewModel()
) {
    CompositionLocalProvider(LocalBehavior provides NotificationTabBehavior(LocalContext.current)) {

        val actions = LocalBehavior.current

        LaunchedEffect(null) {
            actions.getNotifications(notificationServiceViewModel, notificationTabUiViewModel)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NotificationAppBar()

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                NotificationsList()
                DeleteButton()
            }
        }
    }
}