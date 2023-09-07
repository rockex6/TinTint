package com.rockex6.tintint.datapage.viewmodel

import com.rockex6.tintint.datapage.api.DataApi

class DataRepository(private val dataApi: DataApi) {

    fun getData() = dataApi.getData()
}