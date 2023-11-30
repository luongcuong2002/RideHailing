package com.cuongnl.ridehailing.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.util.Locale

fun Context.setAppLocale(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}

fun Context.showDialog(
    title: String,
    message: String,
    textOfNegativeButton: String? = null,
    textOfPositiveButton: String,
    positiveButtonFunction: () -> Unit,
    negativeButtonFunction: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)

    if (textOfNegativeButton != null) {
        builder.setNegativeButton(
            textOfNegativeButton
        ) { dialog, id ->
            if (negativeButtonFunction != null) {
                negativeButtonFunction()
            }
            dialog.dismiss()
        }
    }

    builder.setPositiveButton(
        textOfPositiveButton
    ) { dialog, id ->
        positiveButtonFunction()
        dialog.dismiss()
    }
    val alert = builder.create()
    alert.show()
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

fun Context.bitmapDescriptorFromVector(resId: Int, size: Int = 150): BitmapDescriptor? {
    return ContextCompat.getDrawable(this, resId)?.run {
        setBounds(0, 0, size, size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics)