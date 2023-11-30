package com.cuongnl.ridehailing.callbacks.api

import retrofit2.Call
import retrofit2.Response

interface BaseApiCallback<T> {
    fun onFinish()
    fun onFailure(call: Call<T>, t: Throwable)
}