package com.cuongnl.ridehailing.screens.tokencheck

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.api.GetUserResponse
import com.cuongnl.ridehailing.models.User
import com.cuongnl.ridehailing.screens.permission.PermissionActivity
import com.cuongnl.ridehailing.viewmodel.apiservice.UserServiceViewModel

class TokenCheckActivity : BaseActivity() {

    private lateinit var userServiceViewModel: UserServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        userServiceViewModel = ViewModelProvider(this)[UserServiceViewModel::class.java]

        userServiceViewModel.getUser(
            object : SimpleApiCallback<GetUserResponse> {
                override fun onSuccess(
                    call: retrofit2.Call<GetUserResponse>,
                    response: retrofit2.Response<GetUserResponse>
                ) {
                    val data = response.body()
                    data?.let {

                        val user = User().apply {
                            setUserName(it.userName)
                            setPhoneNumber(it.phoneNumber)
                            setEmail(it.email)
                        }

                        CurrentUser.setUser(user)
                    }
                }

                override fun onError(
                    call: retrofit2.Call<GetUserResponse>,
                    response: retrofit2.Response<GetUserResponse>
                ) {

                }

                override fun onFailure(call: retrofit2.Call<GetUserResponse>, t: Throwable) {

                }

                override fun onFinish() {
                    navigateToNextActivity()
                }
            }
        )

        splashScreen.setKeepOnScreenCondition {
            true
        }
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, PermissionActivity::class.java)
        startActivity(intent)
    }
}