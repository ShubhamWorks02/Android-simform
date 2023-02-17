package com.example.android.marsphotos.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.data.remote.MarsPhoto
import com.example.android.marsphotos.databinding.GridViewItemBinding

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var originalList = listOf<MarsPhoto>()
    private val tempList = mutableListOf<MarsPhoto>()

    class ViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MarsPhoto) {
            binding.photo = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = tempList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tempList[position])
    }

    fun submitList(list: List<MarsPhoto>) {
        tempList.clear()
        tempList.addAll(list)
        originalList = list
        notifyDataSetChanged()
    }

}