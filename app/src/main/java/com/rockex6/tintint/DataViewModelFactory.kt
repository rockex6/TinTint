package com.rockex6.tintint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.rockex6.tintint.viewmodel.DataRepository
import com.rockex6.tintint.viewmodel.DataViewModel


class DataViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(repository) as T
    }

}