package com.rockex6.tintint

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rockex6.tintint.databinding.ItemDataBinding
import com.rockex6.tintint.model.DataModel


class DataListAdapter(private val dataList: List<DataModel>) : Adapter<DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding, parent.width / 4)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(dataList[position])
    }
}


class DataViewHolder(private val binding: ItemDataBinding, private val parentWidth: Int) : ViewHolder(binding.root) {
    fun bind(data: DataModel) {

        binding.dataTitle.text = data.title
        binding.dataId.text = data.id.toString()
//        binding.root.layoutParams.width = parentWidth / 4
//        binding.root.layoutParams.height = parentWidth / 4
        binding.dataThumbnail.loadImage(binding.dataThumbnail.context, data.thumbnailUrl)
    }
}

