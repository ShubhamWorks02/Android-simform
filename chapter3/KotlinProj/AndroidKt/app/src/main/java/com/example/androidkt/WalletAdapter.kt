package com.example.androidkt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkt.databinding.ItemCardBinding

class WalletAdapter : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    private var originalList = listOf<Wallet>()
    private val tempList = mutableListOf<Wallet>()

    class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Wallet) {
            binding.wallet = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = tempList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tempList[position])
    }

    fun submitList(list: List<Wallet>) {
        tempList.clear()
        tempList.addAll(list)
        originalList = list
        notifyDataSetChanged()
    }

    fun addWallet(wallet: Wallet) {
        tempList.add(wallet)
        notifyItemChanged(tempList.size - 1)
    }
}