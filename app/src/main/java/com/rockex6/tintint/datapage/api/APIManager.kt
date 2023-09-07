package com.rockex6.tintint.datapage.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {

    val api: DataApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/albums/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DataApi::class.java)
}