package com.rockex6.tintint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rockex6.tintint.api.APIManager
import com.rockex6.tintint.databinding.ActivityDataBinding
import com.rockex6.tintint.viewmodel.DataRepository
import com.rockex6.tintint.viewmodel.DataViewModel

class DataListFragment : Fragment() {

    private lateinit var binding: ActivityDataBinding
    private lateinit var viewModel: DataViewModel
    private var mDataListAdapter: DataListAdapter? = null
    private var isLoading = false

    override fun onStart() {
        super.onStart()
        observeData()
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityDataBinding.inflate(layoutInflater)
        val repo = DataRepository(APIManager.api)
        val vmFactory = DataViewModelFactory(repo)
        viewModel = ViewModelProvider(this, vmFactory)[DataViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mDataListAdapter = DataListAdapter()
        binding.list.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = mDataListAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (!isLoading && lastVisibleItemPosition == totalItemCount - 1) {
                        loadMoreData()
                    }
                }
            })
        }
    }

    private fun observeData() {
        viewModel.mDataList.observe(this) { dataList ->
            hideProgressBar()
            mDataListAdapter?.setData(dataList)
        }

        viewModel.errorMessage.observe(this) {
            hideProgressBar()
            it?.let { showErrorMessage(it) }
        }
        viewModel.isLoading.observe(this) {
            isLoading = it
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun loadMoreData() {
        mDataListAdapter?.addLoadingItem()
        viewModel.loadMore()
    }
}