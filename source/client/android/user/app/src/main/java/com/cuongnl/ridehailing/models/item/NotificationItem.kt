package com.cuongnl.ridehailing.models.item

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.cuongnl.ridehailing.models.Notification

data class NotificationItem(
    val notification: Notification,
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
)
