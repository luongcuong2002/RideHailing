package com.cuongnl.ridehailing.callbacks.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RetrofitCallback<T>(private val apiCallbacks: BaseApiCallback<T>) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        apiCallbacks.onFinish()
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        apiCallbacks.onFailure(call, t)
        apiCallbacks.onFinish()
    }
}