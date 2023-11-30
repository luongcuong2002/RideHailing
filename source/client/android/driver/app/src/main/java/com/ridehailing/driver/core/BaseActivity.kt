package com.ridehailing.driver.core

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ridehailing.driver.extensions.setAppLocale
import com.ridehailing.driver.utils.Constant
import com.ridehailing.driver.utils.LocalStorageUtils

abstract class BaseActivity : AppCompatActivity() {

    private var activityLang: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLang = LocalStorageUtils.readData(this, LocalStorageUtils.KEY_LANGUAGE) as String?

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    override fun attachBaseContext(newBase: Context) {
        val languageCode = LocalStorageUtils.readData(newBase, LocalStorageUtils.KEY_LANGUAGE) as String?
        if (languageCode == null) {
            super.attachBaseContext(ContextWrapper(newBase))
        } else {
            super.attachBaseContext(ContextWrapper(newBase.setAppLocale(languageCode)))
        }
    }

    override fun onResume() {
        super.onResume()

        val storedLang: String? = LocalStorageUtils.readData(this, LocalStorageUtils.KEY_LANGUAGE) as String?

        storedLang?.let {
            if (activityLang != storedLang) {
                recreate()
            }
        }
    }
}