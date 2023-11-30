package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.item.NotificationItem

class NotificationTabUiViewModel : ViewModel() {

    private var _isDeleting = mutableStateOf(false)

    val isDeleting : State<Boolean> = _isDeleting

    val listNotifications = mutableStateListOf<NotificationItem>()

    fun setIsDeleting(isDeleting: Boolean) {

        if (!isDeleting) {
            unSelectAllNotifications()
        }

        _isDeleting.value = isDeleting
    }

    fun addNotification(notification: NotificationItem) {
        listNotifications.add(notification)
    }

    fun removeNotificationById(notificationId: Int) {
        listNotifications.removeIf { it.notification.id == notificationId }
    }

    fun isMoreNotificationsSelected() : Boolean {
        return listNotifications.filter { it.isSelected.value }.size > 1
    }

    private fun unSelectAllNotifications() {
        listNotifications.forEach { it.isSelected.value = false }
    }
}