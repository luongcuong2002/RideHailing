package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.api.LoginRequest

class PasswordVerificationViewModel : ViewModel() {

    private var _internationalPhoneNumber = mutableStateOf("")
    private var _passwordTextInput = mutableStateOf("")
    private var _isWrongPassword = mutableStateOf(false)

    val internationalPhoneNumber: State<String> = _internationalPhoneNumber
    val isWrongPassword: State<Boolean> = _isWrongPassword

    fun setInternationalPhoneNumber(phoneNumber: String) {
        _internationalPhoneNumber.value = phoneNumber
    }

    fun setIsWrongPassword(isWrongPassword: Boolean) {
        _isWrongPassword.value = isWrongPassword
    }

    fun setPasswordTextInput(passwordTextInput: String) {
        _passwordTextInput.value = passwordTextInput
    }

    fun getLoginRequest() : LoginRequest {
        return LoginRequest(_internationalPhoneNumber.value, _passwordTextInput.value)
    }
}