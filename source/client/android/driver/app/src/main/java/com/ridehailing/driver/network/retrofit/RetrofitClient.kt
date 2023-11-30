package com.ridehailing.driver.network.retrofit

import android.content.Context
import com.ridehailing.driver.computerlocal.ServerAddress
import com.ridehailing.driver.utils.LocalStorageUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {

        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            if (retrofit == null) {
                throw Exception("RetrofitClient has not been initialized")
            }
            return retrofit!!
        }

        fun createClient(context: Context) {
            val httpClient = setupOkHttpClient(context)

            retrofit = Retrofit.Builder()
                .baseUrl(ServerAddress.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }

        private fun setupOkHttpClient(context: Context) : OkHttpClient {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val originalRequest: Request = chain.request()

                val accessToken = LocalStorageUtils.readData(context, LocalStorageUtils.KEY_ACCESS_TOKEN) as String?

                val builder: Request.Builder = originalRequest.newBuilder().header(
                    "Authorization",
                    "Bearer $accessToken"
                )
                val newRequest: Request = builder.build()
                chain.proceed(newRequest)
            }

            return httpClient.build()
        }
    }
}