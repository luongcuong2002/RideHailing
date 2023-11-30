package com.cuongnl.ridehailing.viewmodel.apiservice

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.callbacks.api.LoginCallback
import com.cuongnl.ridehailing.callbacks.api.RetrofitCallback
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.callbacks.api.UserCheckCallback
import com.cuongnl.ridehailing.models.api.LoginRequest
import com.cuongnl.ridehailing.models.api.LoginResponse
import com.cuongnl.ridehailing.models.api.RegisterRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import com.cuongnl.ridehailing.network.retrofit.repository.AuthRepository
import retrofit2.Call
import retrofit2.Response

class AuthServiceViewModel : ViewModel() {

    private var authRepository: AuthRepository = AuthRepository()

    fun checkExistingUser(
        phoneNumber: String,
        userCheckCallback: UserCheckCallback<ScalarsBooleanResponse>
    ) {
        authRepository.checkExistingUser(phoneNumber, object : RetrofitCallback<ScalarsBooleanResponse>(userCheckCallback) {
            override fun onResponse(
                call: Call<ScalarsBooleanResponse>,
                response: Response<ScalarsBooleanResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        if (it) {
                            userCheckCallback.onUserExisting()
                        } else {
                            userCheckCallback.onUserNotExisting()
                        }
                    }
                }
            }
        })
    }

    fun login(
        loginRequest: LoginRequest,
        loginCallback: LoginCallback<LoginResponse>
    ) {
        authRepository.login(loginRequest, object : RetrofitCallback<LoginResponse>(loginCallback) {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    val accessToken = response.body()?.accessToken
                    val userData = response.body()?.userData

                    if (accessToken != null && userData != null) {
                        loginCallback.onSuccessfulLogin(accessToken, userData)
                    } else if (accessToken == null) {
                        loginCallback.onNoAccessTokenProvided()
                    } else {
                        loginCallback.onNoUserDataProvided()
                    }
                } else if (response.code() == 401) {
                    loginCallback.onWrongPassword()
                }
            }
        })
    }

    fun register(
        registerRequest: RegisterRequest,
        simpleApiCallback: SimpleApiCallback<ScalarsBooleanResponse>
    ) {
        authRepository.register(registerRequest, object : RetrofitCallback<ScalarsBooleanResponse>(simpleApiCallback) {
            override fun onResponse(
                call: Call<ScalarsBooleanResponse>,
                response: Response<ScalarsBooleanResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    simpleApiCallback.onSuccess(call, response)
                }
            }
        })
    }
}