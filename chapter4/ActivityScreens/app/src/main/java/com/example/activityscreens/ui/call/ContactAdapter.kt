package com.example.activityscreens.ui.call

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.activityscreens.data.remote.response.UserDetails
import com.example.activityscreens.databinding.ItemContactBinding

class UserAdapter(private val onItemClick: (UserDetails) -> Unit) :
    ListAdapter<UserDetails, UserAdapter.ViewHolder>(UserDiffCallback()) {

    var filterableList: List<UserDetails> = emptyList()
    public override fun getItem(position: Int): UserDetails {
        return currentList[position]
    }

    inner class ViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserDetails) {
            binding.data = data
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val user = getItem(position)
                    onItemClick(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun filterData(searchedQuery: String) {
        val list = filterableList.filter {
            it.firstName.lowercase().contains(searchedQuery.lowercase())
                    || it.lastName.lowercase().contains(searchedQuery.lowercase())
        }
        submitList(list)
    }

}

class UserDiffCallback : DiffUtil.ItemCallback<UserDetails>() {

    override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
        return oldItem == newItem
    }

}