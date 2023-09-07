package com.rockex6.tintint.viewmodel

class DataRepository(private val dataApi: DataApi) {

    fun getData() = dataApi.getData()
}