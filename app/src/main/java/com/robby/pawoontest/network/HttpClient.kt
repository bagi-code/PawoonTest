package com.robby.pawoontest.network

import com.readystatesoftware.chuck.ChuckInterceptor
import com.robby.pawoontest.BuildConfig
import com.robby.pawoontest.Pawoon
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {
    private var client: Retrofit? = null
    private var endpoint: Endpoint? = null

    companion object {
        private val mInstance: HttpClient = HttpClient()
        @Synchronized
        fun getInstance(): HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi(): Endpoint? {
        if (endpoint == null) {
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    fun buildRetrofitClient() {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(ChuckInterceptor(Pawoon.getApp()))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        endpoint = null
    }
}