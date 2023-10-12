package com.cuongnl.ridehailing.core

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cuongnl.ridehailing.extensions.setAppLocale
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.LocalStorageUtils

abstract class BaseActivity : AppCompatActivity() {

    private var activityLang : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flags =
            (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.decorView.systemUiVisibility = flags

        activityLang = LocalStorageUtils.readData(this, Constant.KEY_LANGUAGE) as String?
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val languageCode = LocalStorageUtils.readData(newBase, Constant.KEY_LANGUAGE) as String?
        if(languageCode == null){
            super.attachBaseContext(ContextWrapper(newBase))
        } else {
            super.attachBaseContext(ContextWrapper(newBase.setAppLocale(languageCode)))
        }
    }

    override fun onResume() {
        super.onResume()

        val storedLang: String? = LocalStorageUtils.readData(this, Constant.KEY_LANGUAGE) as String?

        storedLang?.let {
            if(activityLang != storedLang){
                recreate()
            }
        }
    }
}