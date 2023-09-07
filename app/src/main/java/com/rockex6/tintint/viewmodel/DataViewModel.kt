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
    private val mAllDataTemp = ArrayList<DataModel>()

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
                        mAllDataTemp.addAll(dataList)
                        mDataList.postValue(getCurrentData(mAllDataTemp))
                    },
                    onError = { e ->
                        isLoading.value = false
                        showErrorMessage(e.message)
                    }
                )
        )
    }

    fun loadMore() {
        isLoading.value = true
        page++
        try {
            val newData = getCurrentData(mAllDataTemp)
            if (newData.isNotEmpty()) {
                mDataList.value = newData
            }
            isLoading.value = false
        } catch (e: Exception) {
            showErrorMessage(e.message)
        }
    }

    private fun getCurrentData(dataList: List<DataModel>): List<DataModel> {
        if (page * 50 > dataList.size) return ArrayList()
        val firstPosition = if (page == 1) {
            0
        } else if ((page - 1) * 50 > dataList.size) {
            (page - 1) * 50 - dataList.size
        } else {
            (page - 1) * 50
        }
        val lastPosition = if (page * 50 > dataList.size) {
            dataList.size
        } else {
            page * 50
        }
        return dataList.subList(firstPosition, lastPosition)
    }

    private fun showErrorMessage(e: String?) {
        errorMessage.value = e
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}