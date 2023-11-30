package com.cuongnl.ridehailing.screens.home.tab.notification.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.home.tab.notification.LocalBehavior
import com.cuongnl.ridehailing.viewmodel.NotificationTabUiViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.NotificationServiceViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BoxScope.DeleteButton(
    notificationTabUiViewModel: NotificationTabUiViewModel = viewModel(),
    notificationServiceViewModel: NotificationServiceViewModel = viewModel()
) {

    val actions = LocalBehavior.current

    val size = notificationTabUiViewModel.listNotifications.filter { it.isSelected.value }.size

    if (size > 0 && notificationTabUiViewModel.isDeleting.value) {

        NoRippleButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 15.sdp, end = 15.sdp, bottom = 10.sdp)
                .clip(RoundedCornerShape(25))
                .fillMaxWidth()
                .background(colorResource(id = R.color.orange_500)),
            onClick = {
                actions.removeNotifications(notificationTabUiViewModel, notificationServiceViewModel)
            }
        ) {
            AppText(
                text = "${stringResource(id = R.string.delete)} ${size} ${stringResource(id = R.string.notification)}",
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.sdp)
            )
        }
    }
}