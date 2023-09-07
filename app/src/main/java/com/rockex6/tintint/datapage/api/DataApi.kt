package com.rockex6.tintint.datapage.api

import com.rockex6.tintint.datapage.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {
    @GET("photos")
    fun getData(): Single<List<DataModel>>
}