package com.rockex6.tintint.api

import com.rockex6.tintint.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {
    @GET("photos")
    fun getDataAPI(): Single<List<DataModel>>

}