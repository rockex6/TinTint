package com.rockex6.tintint.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rockex6.tintint.viewmodel.DataApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object APIManager {

    val api: DataApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DataApi::class.java)
}