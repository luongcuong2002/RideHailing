package com.cuongnl.ridehailing.callbacks.api

interface UserCheckCallback<T> : BaseApiCallback<T>{
    fun onUserExisting()
    fun onUserNotExisting()
}