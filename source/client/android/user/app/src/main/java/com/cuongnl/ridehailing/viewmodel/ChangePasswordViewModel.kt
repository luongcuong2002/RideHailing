package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChangePasswordViewModel : ViewModel() {

    private var _passwordInputText = ""
    private var _isPasswordVisible = mutableStateOf(false)
    private var _enableUserPasswordNextButton = mutableStateOf(false)

    val isPasswordVisible : State<Boolean> = _isPasswordVisible
    val enableUserPasswordNextButton : State<Boolean> = _enableUserPasswordNextButton

    fun setPasswordInputText(passwordInputText: String) {
        _passwordInputText = passwordInputText
        checkUserPasswordNextButtonState()
    }

    fun setPasswordVisibility(isVisible: Boolean) {
        _isPasswordVisible.value = isVisible
    }

    fun checkUserPasswordNextButtonState() {
        _enableUserPasswordNextButton.value = _passwordInputText.length == 6
    }

    fun getPasswordInputText() : String {
        return _passwordInputText
    }
}