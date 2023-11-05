package com.cuongnl.ridehailing.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class User {

    private var _userName : MutableState<String?> = mutableStateOf(null)
    private var _phoneNumber : MutableState<String?> = mutableStateOf(null)
    private var _email : MutableState<String?> = mutableStateOf(null)
    private var _addresses = mutableStateListOf<Address>()
    private var _notifications = mutableStateListOf<Notification>()

    val userName : State<String?> = _userName
    val phoneNumber : State<String?> = _phoneNumber
    val email : State<String?> = _email
    val addresses : List<Address> = _addresses
    val notifications : List<Notification> = _notifications

    fun setUserName(userName: String) {
        _userName.value = userName
    }

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun setEmail(email: String?) {
        _email.value = email
    }

    fun addAddress(address: Address) {
        _addresses.add(address)
    }

    fun addAddresses(addresses: List<Address>) {
        _addresses.addAll(addresses)
    }

    fun clearAddresses() {
        _addresses.clear()
    }

    fun clearNotifications() {
        _notifications.clear()
    }

    fun addNotifications(notifications: List<Notification>) {
        _notifications.addAll(notifications)
    }

    fun removeNotification(index: Int) {
        _notifications.removeAt(index)
    }
}
