package com.cuongnl.ridehailing.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object SystemUtils {
    fun goToAppInfo(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)
    }
}