package com.example.androidkt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkt.TransactionFragment.TransactionType
import com.example.androidkt.databinding.ItemTransactionBinding
import java.util.logging.Filter

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private val originalList = mutableListOf<Transaction>()
    private var filteredList = listOf<Transaction>()

    class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Transaction) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    fun submitList(list: List<Transaction>) {
        originalList.clear()
        originalList.addAll(list)
        filteredList = list
        notifyDataSetChanged()
    }

    private fun setFilteredList(list: List<Transaction>) {
        filteredList = list
        notifyDataSetChanged()
    }

    fun filterData(query: String, transactionType: TransactionType) {
         val filteredList = originalList.filter {
            if (transactionType == TransactionType.BOTH) {
                true
            } else {
                if (transactionType == TransactionType.EXPENSE && it.isExpense) {
                    true
                } else transactionType == TransactionType.INCOME && !it.isExpense
            }
        }.filter {
            query.isEmpty() || it.bankName.contains(query,ignoreCase = true)
        }
        setFilteredList(filteredList)
    }
}