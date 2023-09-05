package com.rockex6.tintint.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockex6.tintint.api.APIManager
import com.rockex6.tintint.api.APIService
import com.rockex6.tintint.model.DataModel

class DataViewModel(private val repository: DataRepository) : ViewModel() {
    fun getData() = repository.getData()
}