package com.rockex6.tintint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rockex6.tintint.api.APIManager
import com.rockex6.tintint.databinding.ActivityDataBinding
import com.rockex6.tintint.model.DataModel
import com.rockex6.tintint.viewmodel.DataRepository
import com.rockex6.tintint.viewmodel.DataViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding
    private lateinit var viewModel: DataViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        val repo = DataRepository(APIManager.api)
        val vmFactory = DataViewModelFactory(repo)
        viewModel = ViewModelProvider(this, vmFactory)[DataViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable += viewModel.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { dataList ->
                    initRecyclerView(dataList)
                },
                onError = { e -> }
            )
    }

    private fun initRecyclerView(dataList: List<DataModel>) {
        binding.list.apply {
            layoutManager = GridLayoutManager(this@DataActivity, 4)
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }


}