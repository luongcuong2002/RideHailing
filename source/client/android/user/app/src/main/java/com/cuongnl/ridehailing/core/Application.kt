package com.cuongnl.ridehailing.core

import com.cuongnl.ridehailing.BuildConfig
import com.cuongnl.ridehailing.network.retrofit.RetrofitClient
import com.google.android.libraries.places.api.Places

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.createClient(applicationContext)

        Places.initialize(applicationContext, BuildConfig.MAPS_API_KEY)
    }

}