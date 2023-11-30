package com.cuongnl.ridehailing.screens.passwordverification

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.IPasswordVerificationActivityBehavior
import com.cuongnl.ridehailing.callbacks.api.LoginCallback
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.OtpAuthType
import com.cuongnl.ridehailing.extensions.showDialog
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.api.GetUserResponse
import com.cuongnl.ridehailing.models.api.LoginResponse
import com.cuongnl.ridehailing.models.User
import com.cuongnl.ridehailing.screens.home.HomeActivity
import com.cuongnl.ridehailing.screens.otpverification.OtpVerificationActivity
import com.cuongnl.ridehailing.screens.passwordverification.ui.ChangePhoneNumberButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.ForgotPasswordButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.HelloText
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordOtpTextField
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordPromptText
import com.cuongnl.ridehailing.screens.passwordverification.ui.WrongPasswordText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.LocalStorageUtils
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.PasswordVerificationViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.AuthServiceViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp
import retrofit2.Call

val LocalActivityBehavior =
    staticCompositionLocalOf<IPasswordVerificationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class PasswordVerificationActivity : BaseActivity(), IPasswordVerificationActivityBehavior {

    private lateinit var loaderViewModel: LoaderViewModel
    private lateinit var authServiceViewModel: AuthServiceViewModel
    private lateinit var passwordVerificationViewModel: PasswordVerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    override fun popActivity() {
        finish()
    }

    override fun login() {

        val loginRequest = passwordVerificationViewModel.getLoginRequest()

        loaderViewModel.setLoading(true)

        authServiceViewModel.login(
            loginRequest,
            object : LoginCallback<LoginResponse> {
                override fun onFinish() {
                    loaderViewModel.setLoading(false)
                }

                override fun onSuccessfulLogin(accessToken: String, userData: GetUserResponse) {

                    // save access token
                    LocalStorageUtils.writeData(this@PasswordVerificationActivity, LocalStorageUtils.KEY_ACCESS_TOKEN, accessToken)

                    val user = User().apply {
                        setUserName(userData.userName)
                        setPhoneNumber(userData.phoneNumber)
                        setEmail(userData.email)
                    }

                    CurrentUser.setUser(user)

                    val intent = Intent(this@PasswordVerificationActivity, HomeActivity::class.java)
                    startActivity(intent)
                }

                override fun onWrongPassword() {
                    passwordVerificationViewModel.setIsWrongPassword(true)
                }

                override fun onNoAccessTokenProvided() {
                    Toast.makeText(this@PasswordVerificationActivity, "No access token provided!", Toast.LENGTH_SHORT).show()
                }

                override fun onNoUserDataProvided() {
                    Toast.makeText(this@PasswordVerificationActivity, "No user data provided!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@PasswordVerificationActivity, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    override fun showForgotPasswordDialog() {
        showDialog(
            title = getString(R.string.confirm_text),
            message = getString(R.string.forgot_password_dialog_prompt),
            textOfNegativeButton = getString(R.string.cancel_text),
            textOfPositiveButton = getString(R.string.ok_text),
            positiveButtonFunction = {
                navigateToOtpVerificationActivity()
            }
        )
    }

    private fun setupViewModel(){

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER)

        passwordVerificationViewModel = ViewModelProvider(this)[PasswordVerificationViewModel::class.java]
        passwordVerificationViewModel.setInternationalPhoneNumber(phoneNumber!!)

        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]
        authServiceViewModel = ViewModelProvider(this)[AuthServiceViewModel::class.java]
    }

    private fun navigateToOtpVerificationActivity(){

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER)

        val intent = Intent(this, OtpVerificationActivity::class.java)
        intent.putExtra(Constant.BUNDLE_OTP_AUTH_TYPE, OtpAuthType.PASSWORD_CHANGING)
        intent.putExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER, phoneNumber)
        startActivity(intent)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .padding(top = 80.sdp, start = 15.sdp, end = 15.sdp)
            ) {
                HelloText()
                PasswordPromptText()
                PasswordOtpTextField()
                WrongPasswordText()

                Row(
                    modifier = Modifier
                        .padding(top = 15.sdp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ForgotPasswordButton()
                    ChangePhoneNumberButton()
                }
            }
        }
    }
}