package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserInfoCreationViewModel : ViewModel() {
    private var _userName = ""
    private var _userEmail = ""
    private var _userPassword = ""

    private var _isPasswordCreationBottomSheetVisible = mutableStateOf(false)
    private var _enableUserNameNextButton = mutableStateOf(false)
    private var _enableUserPasswordNextButton = mutableStateOf(false)
    private var _isPasswordVisible = mutableStateOf(false)

    val isPasswordCreationBottomSheetVisible : State<Boolean> = _isPasswordCreationBottomSheetVisible
    val enableUserNameNextButton : State<Boolean> = _enableUserNameNextButton
    val enableUserPasswordNextButton : State<Boolean> = _enableUserPasswordNextButton
    val isPasswordVisible : State<Boolean> = _isPasswordVisible

    fun setUserName(userName: String) {
        _userName = userName
        checkUserNameNextButtonState()
    }

    fun setUserEmail(userEmail: String) {
        _userEmail = userEmail
    }

    fun setUserPassword(userPassword: String) {
        _userPassword = userPassword
        checkUserPasswordNextButtonState()
    }

    fun getUserName(): String {
        return _userName
    }

    fun getUserEmail(): String {
        return _userEmail
    }

    fun getUserPassword(): String {
        return _userPassword
    }

    fun checkUserNameNextButtonState() {
        _enableUserNameNextButton.value = _userName.isNotEmpty()
    }

    fun checkUserPasswordNextButtonState() {
        _enableUserPasswordNextButton.value = _userPassword.length == 6
    }

    fun setBottomSheetVisible(isVisible: Boolean) {
        _isPasswordCreationBottomSheetVisible.value = isVisible
    }

    fun setPasswordVisibility(isVisible: Boolean) {
        _isPasswordVisible.value = isVisible
    }
}