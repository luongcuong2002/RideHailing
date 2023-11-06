package com.cuongnl.ridehailing.screens.home.tab.notification.behavior

import android.content.Context
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.showDialog
import com.cuongnl.ridehailing.models.Notification
import com.cuongnl.ridehailing.models.item.NotificationItem
import com.cuongnl.ridehailing.viewmodel.NotificationTabUiViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.NotificationServiceViewModel

class NotificationTabBehavior(val context: Context) {

    fun getNotifications(
        notificationServiceViewModel: NotificationServiceViewModel,
        notificationTabUiViewModel: NotificationTabUiViewModel
    ) {

        notificationTabUiViewModel.apply {
            addNotification(
                NotificationItem(
                    Notification(
                        id = 1,
                        date = "05/11/2023",
                        title = "Bike tặng Hà Nội tới 20K \uD83D\uDE0D",
                        shortContent = "⛅ 2 mã 10% tối đa 20k\n\uD83C\uDF08 Mở app đặt xe ngay",
                        content = "Chào mừng bạn đến với ứng dụng",
                        isRead = false,
                        base64Image = ""
                    )
                )
            )
            addNotification(
                NotificationItem(
                    Notification(
                        id = 2,
                        date = "03/11/2023",
                        title = "Mã 15% Bike tặng Sài Gòn",
                        shortContent = "\uD83D\uDE0D Đặt Cam SM Bike bon bon\n\uD83D\uDC4F Mưa hay nắng bác tài Cam vẫn đón\n\uD83D\uDC9A Mở app đặt xe ưu đãi tới 40%",
                        content = "Chào mừng bạn đến với ứng dụng",
                        isRead = true,
                        base64Image = ""
                    )
                )
            )
        }

//    notificationServiceViewModel.getNotifications(object : SimpleApiCallback<NotificationResponse> {
//        override fun onSuccess(call: Call<NotificationResponse>, response: Response<NotificationResponse>) {
//            response.body()?.let {
//                CurrentUser.getUser()?.clearNotifications()
//                CurrentUser.getUser()?.addNotifications(it.notifications)
//            }
//        }
//
//        override fun onFinish() {
//
//        }
//
//        override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
//
//        }
//
//        override fun onError(
//            call: Call<NotificationResponse>,
//            response: Response<NotificationResponse>
//        ) {
//
//        }
//    })
    }

    fun showDeleteAllNotificationDialog() {
        context.showDialog(
            title = context.getString(R.string.dialog_delete_all_notification_title),
            message = context.getString(R.string.dialog_delete_all_notification_content),
            textOfNegativeButton = context.getString(R.string.cancel),
            textOfPositiveButton = context.getString(R.string.delete),
            positiveButtonFunction = {

            }
        )
    }

}