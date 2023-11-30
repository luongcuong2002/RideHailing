package com.ridehailing.driver.screens.tokencheck

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.viewmodel.TokenCheckUiViewModel

class TokenCheckActivity : BaseActivity() {

    private lateinit var tokenCheckUiViewModel: TokenCheckUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        tokenCheckUiViewModel = ViewModelProvider(this)[TokenCheckUiViewModel::class.java]

        tokenCheckUiViewModel.fetchUser(this)

        splashScreen.setKeepOnScreenCondition {
            true
        }
    }
}