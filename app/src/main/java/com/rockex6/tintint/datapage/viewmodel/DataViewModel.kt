package com.rockex6.tintint.datapage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockex6.tintint.datapage.model.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DataViewModel(private val repository: DataRepository) : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()
    val mDataList = MutableLiveData<List<DataModel>>()

    private var page = 1
    val isLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String?>()

    fun getData() {
        isLoading.value = true
        mCompositeDisposable.add(
            repository.getData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(

                    onSuccess = { dataList ->
                        isLoading.value = false
                        mDataList.postValue(dataList)
                    },
                    onError = { e ->
                        isLoading.value = false
                        errorMessage.postValue(e.message)
                    }
                )
        )
    }

    fun loadMore() {
        isLoading.value = true
        page++
        getData()
    }


    override fun onCleared() {
        super.onCleared()
        page = 1
        mCompositeDisposable.clear()
    }
}