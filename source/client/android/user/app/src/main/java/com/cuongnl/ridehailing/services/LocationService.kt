package com.cuongnl.ridehailing.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationRequest
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat


class LocationService : Service() {

    private var longitude: Double = 0.toDouble()
    private var latitude: Double = 0.toDouble()
    private var lastLocation: Location? = null
    private lateinit var notificationManager: NotificationManager
    private lateinit var locationRequest: LocationRequest

    override fun onCreate() {
        super.onCreate()

        // Tạo notification
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val notification = NotificationCompat.Builder(this, "location_service")
//            .setContentTitle(getString(R.string.app_name))
//            .setContentText(getString(R.string.dsc_Splash))
//            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(false)
            .build()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "location_service",
                "Location Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Bắt đầu chạy service và hiển thị notification
        try {
            startForeground(101660, notification)
        } catch (e: Exception) {
            try {
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(101660, notification)
            } catch (ignore: Exception) {
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}