package com.example.androidkt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.androidkt.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    enum class TransactionType {
        BOTH,
        EXPENSE,
        INCOME
    }

    private lateinit var binding: FragmentTransactionBinding
    private var selectedTransactionType = TransactionType.BOTH
    private val transactionAdapter by lazy {
        TransactionAdapter()
    }
    private var searchedQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTransactionBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? BottomNavigationVisibilityListener)?.setBottomNavigationVisibility(false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            rvTransactionList.adapter = transactionAdapter
            chipgroupFilter.isSingleSelection = true
            chipAll.isSelected = true
            edtSearch.doOnTextChanged { text, _, _, _ ->
                searchedQuery = text.toString().trim()
                transactionAdapter.filterData(
                    searchedQuery,
                    selectedTransactionType
                )
            }

            imgBackArrow.setOnClickListener {
               parentFragmentManager.popBackStack()
            }

            chipgroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
                checkedIds.firstOrNull()?.let {
                    selectedTransactionType = when (it) {
                        chipAll.id -> TransactionType.BOTH
                        chipExpense.id -> {
                            chipAll.isSelected = false
                            TransactionType.EXPENSE
                        }
                        else -> {
                            chipAll.isSelected = false
                            TransactionType.INCOME }
                    }

                }
                transactionAdapter.filterData(searchedQuery, selectedTransactionType)
            }
        }
        transactionAdapter.submitList(FakeData.transactions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? BottomNavigationVisibilityListener)?.setBottomNavigationVisibility(true)
    }

}