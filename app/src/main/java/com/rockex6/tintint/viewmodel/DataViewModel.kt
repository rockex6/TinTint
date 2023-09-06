package com.rockex6.tintint.viewmodel

import androidx.lifecycle.ViewModel

class DataViewModel(private val repository: DataRepository) : ViewModel() {
    fun getData() = repository.getData()
}