package com.cuongnl.ridehailing.callbacks.api

import retrofit2.Call
import retrofit2.Response

interface SimpleApiCallback<T> : BaseApiCallback<T> {
    fun onSuccess(call: Call<T>, response: Response<T>)
    fun onError(call: Call<T>, response: Response<T>)
}