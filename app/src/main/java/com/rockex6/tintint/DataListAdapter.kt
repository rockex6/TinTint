package com.rockex6.tintint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rockex6.tintint.databinding.ItemDataBinding
import com.rockex6.tintint.databinding.ItemLoadingBinding
import com.rockex6.tintint.model.DataModel


class DataListAdapter : Adapter<ViewHolder>() {
    private val mDataList = ArrayList<DataModel>()

    private val VIEW_TYPE_DATA = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DATA -> {
                val binding =
                    ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DataViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            holder.bind(mDataList[position])
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].type
    }

    fun addLoadingItem() {
        if (mDataList[mDataList.size - 1].type == VIEW_TYPE_LOADING) return
        mDataList.add(DataModel(1))
        notifyItemInserted(mDataList.size)
    }

    private fun removeLoadingItem() {
        if (mDataList.size > 0 && mDataList[mDataList.size - 1].type == VIEW_TYPE_LOADING) {
            mDataList.removeAt(mDataList.size - 1)
            notifyItemRemoved(mDataList.size)
        }
    }

    fun setData(dataList: List<DataModel>) {
        removeLoadingItem()
        val oldLastPosition = if (mDataList.size == 0) {
            0
        } else {
            itemCount - 1
        }
        mDataList.addAll(dataList)
        val newLastPosition = itemCount - 1

        notifyItemRangeChanged(oldLastPosition, newLastPosition)
    }
}


class DataViewHolder(private val binding: ItemDataBinding) : ViewHolder(binding.root) {
    fun bind(data: DataModel) {
        val size = binding.dataThumbnail.resources.displayMetrics.widthPixels / 4
        binding.dataTitle.text = data.title
        binding.dataId.text = data.id.toString()
        binding.root.layoutParams.width = size
        binding.root.layoutParams.height = size
        binding.dataThumbnail.loadImage(binding.dataThumbnail.context, data.thumbnailUrl)
    }
}

class LoadingViewHolder(private val binding: ItemLoadingBinding) : ViewHolder(binding.root) {

    fun bind() {
        val size = binding.root.resources.displayMetrics.widthPixels / 4
        binding.root.layoutParams.width = size
        binding.root.layoutParams.height = size
    }
}

