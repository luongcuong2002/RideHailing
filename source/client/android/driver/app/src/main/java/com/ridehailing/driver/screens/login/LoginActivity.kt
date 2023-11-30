package com.ridehailing.driver.screens.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.screens.login.ui.EmulatorText
import com.ridehailing.driver.screens.login.ui.LoginButton
import com.ridehailing.driver.screens.login.ui.Logo
import com.ridehailing.driver.screens.login.ui.PasswordTextField
import com.ridehailing.driver.screens.login.ui.PhoneNumberTextField
import com.ridehailing.driver.theme.AppTheme

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Screen()
            }
        }
    }
}

@Composable
private fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmulatorText()
        Logo()
        PhoneNumberTextField()
        PasswordTextField()
        LoginButton()
    }
}