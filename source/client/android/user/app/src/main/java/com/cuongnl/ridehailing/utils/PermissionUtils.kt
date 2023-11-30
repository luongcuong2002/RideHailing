package com.cuongnl.ridehailing.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.showDialog

object PermissionUtils {

    val LOCATION_REQUEST_CODE = 1

    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPreciseLocationPermission(activity: Activity, requestCode: Int) {
        requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            requestCode
        )
    }

    fun requestLocationPermissions(activity: Activity, requestCode: Int) {
        requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            requestCode
        )
    }

    fun requestPermission(activity: Activity, permission: String, requestCode: Int) {

        val showRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)

        if (showRationale) {
            showLocationPermissionRationaleDialog(activity)
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                requestCode
            )
        }
    }

    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        val showRationale = permissions.any {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
        }

        if (showRationale) {
            showLocationPermissionRationaleDialog(activity)
        } else {
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                requestCode
            )
        }
    }

    private fun showLocationPermissionRationaleDialog(context: Context) {
        context.showDialog(
            title = context.getString(R.string.dialog_permission_rationale_header),
            message = context.getString(R.string.dialog_permission_rationale_des),
            textOfPositiveButton = context.getString(R.string.continue_text),
            positiveButtonFunction = {
                SystemUtils.goToAppInfo(context)
            }
        )
    }
}