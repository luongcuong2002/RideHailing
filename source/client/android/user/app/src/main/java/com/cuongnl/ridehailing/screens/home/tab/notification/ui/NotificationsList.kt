package com.cuongnl.ridehailing.screens.home.tab.notification.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Notification
import com.cuongnl.ridehailing.models.item.NotificationItem
import com.cuongnl.ridehailing.screens.home.tab.notification.LocalBehavior
import com.cuongnl.ridehailing.viewmodel.NotificationTabUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun NotificationsList(notificationTabUiViewModel: NotificationTabUiViewModel = viewModel()) {

    val data = notificationTabUiViewModel.listNotifications

    LazyColumn(
        modifier = Modifier
            .padding(top = 4.sdp)
            .fillMaxSize(),
        content = {
            items(data.size) { index ->
                NotificationItem(
                    item = data[index]
                )
            }
        }
    )
}

@Composable
private fun NotificationItem(
    item: NotificationItem,
    notificationTabUiViewModel: NotificationTabUiViewModel = viewModel()
) {

    val actions = LocalBehavior.current

    NoRippleButton(
        onClick = {
            if (notificationTabUiViewModel.isDeleting.value) {
                item.isSelected.value = !item.isSelected.value
            } else {
                actions.navigateToNotificationDetail()
            }
        }
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 12.sdp, vertical = 15.sdp),
        ) {

            if (notificationTabUiViewModel.isDeleting.value) {
                if (item.isSelected.value) {
                    Box(
                        modifier = Modifier
                            .padding(end = 7.sdp)
                            .clip(RoundedCornerShape(10.sdp))
                            .size(15.sdp)
                            .background(colorResource(id = R.color.green_600))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxSize()
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(end = 7.sdp)
                            .clip(RoundedCornerShape(10.sdp))
                            .size(15.sdp)
                            .border(
                                2.dp,
                                colorResource(id = R.color.gray_400),
                                RoundedCornerShape(10.sdp)
                            )
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.sdp)
                    .clip(RoundedCornerShape(25))
                    .size(40.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppText(
                        text = item.notification.date,
                        color = colorResource(id = R.color.gray_600),
                        fontSize = 10.sp,
                    )

                    if (!item.notification.isRead) {
                        Box(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .size(8.dp)
                                .background(Color.Red)
                        )
                    }
                }

                AppText(
                    text = item.notification.title,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 5.dp),
                    lineHeight = 20.sp,
                )
                AppText(
                    text = item.notification.shortContent,
                    color = Color.Black,
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                )
            }
        }
    }
}