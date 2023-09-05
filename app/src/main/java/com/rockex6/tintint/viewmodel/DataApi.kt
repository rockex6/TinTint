package com.rockex6.tintint.viewmodel

import com.rockex6.tintint.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET

interface DataApi {
    @GET("photos")
    fun getData(): Single<List<DataModel>>
}