package com.rockex6.tintint.viewmodel

import com.rockex6.tintint.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {
    @GET("{page}/photos")
    fun getData(@Path("page") page: Int): Single<List<DataModel>>
}