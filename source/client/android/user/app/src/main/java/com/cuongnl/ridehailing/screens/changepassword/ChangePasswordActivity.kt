package com.cuongnl.ridehailing.screens.changepassword

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.IChangePasswordActivityBehavior
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.ChangePasswordRequest
import com.cuongnl.ridehailing.models.api.ChangePasswordResponse
import com.cuongnl.ridehailing.screens.changepassword.ui.ContinueButton
import com.cuongnl.ridehailing.screens.changepassword.ui.PasswordTextField
import com.cuongnl.ridehailing.screens.changepassword.ui.PasswordVisibilityButton
import com.cuongnl.ridehailing.screens.changepassword.ui.ResetPasswordText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.ChangePasswordViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.UserServiceViewModel
import com.cuongnl.ridehailing.widgets.BackButton
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp
import retrofit2.Call
import retrofit2.Response

val LocalActivityBehavior =
    staticCompositionLocalOf<IChangePasswordActivityBehavior> { error("No LocalActivityActionsClass provided") }

class ChangePasswordActivity : BaseActivity(), IChangePasswordActivityBehavior {

    private lateinit var userServiceViewModel: UserServiceViewModel
    private lateinit var loaderViewModel: LoaderViewModel
    private lateinit var changePasswordViewModel: ChangePasswordViewModel

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

    override fun changePassword() {

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER)
        val newPassword = changePasswordViewModel.getPasswordInputText()

        val changePasswordRequest = ChangePasswordRequest(phoneNumber!!, newPassword)

        loaderViewModel.setLoading(true)

        userServiceViewModel.changePassword(
            changePasswordRequest,
            object : SimpleApiCallback<ChangePasswordResponse> {
                override fun onSuccess(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {
                    Toast.makeText(
                        this@ChangePasswordActivity,
                        getString(R.string.change_password_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    popActivity()
                }

                override fun onError(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {

                }

                override fun onFinish() {
                    loaderViewModel.setLoading(false)
                }

                override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {

                }
            }
        )

        finish()
    }

    private fun setupViewModel() {
        userServiceViewModel = ViewModelProvider(this)[UserServiceViewModel::class.java]
        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]
        changePasswordViewModel = ViewModelProvider(this)[ChangePasswordViewModel::class.java]
    }
}

@Composable
private fun Screen() {

    val actions = LocalActivityBehavior.current

    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.sdp)
                    .fillMaxSize()
            ) {
                BackButton {
                    actions.popActivity()
                }
                ResetPasswordText()
                PasswordTextField()
                PasswordVisibilityButton()

                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ContinueButton()
                }
            }
        }
    }
}