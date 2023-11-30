package com.cuongnl.ridehailing.utils

import android.content.Context

object LocalStorageUtils {

    private const val BUNDLE_NAME = "APP_BUNDLE_NAME"

    const val KEY_LANGUAGE = "key_language"
    const val KEY_ACCESS_TOKEN = "key_access_token"

    fun writeData(context: Context, key: String, data: Any): Boolean {
        val sharedPref = context.getSharedPreferences(BUNDLE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        when (data) {
            is String -> {
                editor.putString(key, data)
            }

            is Boolean -> {
                editor.putBoolean(key, data)
            }

            is Int -> {
                editor.putInt(key, data)
            }

            is Long -> {
                editor.putLong(key, data)
            }

            is Float -> {
                editor.putFloat(key, data)
            }

            else -> {
                return false
            }
        }
        editor.apply()
        return true
    }

    fun readData(context: Context, key: String): Any? {
        val sharedPref = context.getSharedPreferences(BUNDLE_NAME, Context.MODE_PRIVATE)
        return sharedPref.all[key]
    }
}