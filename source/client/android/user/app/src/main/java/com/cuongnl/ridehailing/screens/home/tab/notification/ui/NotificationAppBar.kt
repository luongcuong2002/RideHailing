package com.cuongnl.ridehailing.screens.home.tab.notification.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.home.tab.notification.LocalBehavior
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.NotificationTabUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun NotificationAppBar(notificationTabUiViewModel: NotificationTabUiViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Constant.APP_BAR_HEIGHT.sdp)
            .background(Color.White)
            .padding(horizontal = 10.sdp)
    ) {
        AppText(
            text = stringResource(id = R.string.notification_app_bar_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 10.sdp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 5.sdp)
        ) {
            if (notificationTabUiViewModel.isMoreNotificationsSelected() && notificationTabUiViewModel.isDeleting.value) {
                DeleteTextButton()
            } else {
                DeleteIcon()
            }
        }
    }
}

@Composable
private fun DeleteTextButton() {

    val actions = LocalBehavior.current

    TouchableOpacityButton(
        onClick = {
            actions.showDeleteAllNotificationDialog()
        }
    ) {
        AppText(
            text = stringResource(id = R.string.delete_all),
            fontSize = 14.sp,
            color = colorResource(id = R.color.app_color),
        )
    }
}

@Composable
private fun DeleteIcon(notificationTabUiViewModel: NotificationTabUiViewModel = viewModel()) {
    TouchableOpacityButton(
        onClick = {
            notificationTabUiViewModel.setIsDeleting(!notificationTabUiViewModel.isDeleting.value)
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_trash),
            contentDescription = null,
            modifier = Modifier
                .size(15.sdp)
        )
    }
}