package com.ridehailing.driver.core

import com.ridehailing.driver.BuildConfig
import com.ridehailing.driver.network.retrofit.RetrofitClient
import com.google.android.libraries.places.api.Places

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.createClient(applicationContext)

        Places.initialize(applicationContext, BuildConfig.MAPS_API_KEY)
    }

}