package com.rockex6.tintint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rockex6.tintint.databinding.ItemDataBinding
import com.rockex6.tintint.model.DataModel


class DataListAdapter : Adapter<DataViewHolder>() {
    private val mDataList = ArrayList<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(mDataList[position])
    }

    fun setData(dataList: List<DataModel>) {
        val oldLastPosition = if(mDataList.size == 0) {
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

