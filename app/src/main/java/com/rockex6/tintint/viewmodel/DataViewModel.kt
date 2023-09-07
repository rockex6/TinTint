package com.rockex6.tintint.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockex6.tintint.model.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DataViewModel(private val repository: DataRepository) : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()
    val mDataList = MutableLiveData<List<DataModel>>()

    private var page = 1
    var isLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String?>()

    fun getData() {
        isLoading.value = true
        mCompositeDisposable.add(
            repository.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { dataList ->
                        isLoading.value = false
                        val firstPosition = if (page == 1) {
                            0
                        } else {
                            (page -1) * 50
                        }
                        mDataList.postValue(dataList.subList(firstPosition, page * 50))
                    },
                    onError = { e ->
                        isLoading.value = false
                        errorMessage.postValue(e.message)
                    }
                )
        )
    }

    fun loadMore() {
        page++
        getData()
    }


    override fun onCleared() {
        super.onCleared()
        page = 1
        mCompositeDisposable.clear()
    }
}