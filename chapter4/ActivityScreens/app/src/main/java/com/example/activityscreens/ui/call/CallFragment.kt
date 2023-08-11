package com.example.activityscreens.ui.call

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activityscreens.databinding.FragmentCallBinding
import com.example.activityscreens.utils.ConnectionManager
import com.example.activityscreens.utils.KeyboardHelper
import com.example.activityscreens.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallFragment : Fragment() {
    private val viewModel: CallViewModel by viewModels()
    private lateinit var binding: FragmentCallBinding
    private lateinit var adapter: UserAdapter
    private var searchedQuery = ""
    private val classTag = "CallFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCallBinding.inflate(inflater).apply {
        lifecycleOwner = this@CallFragment
        viewModel = this@CallFragment.viewModel
        binding = this
    }.root

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter { userDetails ->
            val action = CallFragmentDirections.actionCallFragmentToShowProfileFragment(userDetails)
            findNavController().navigate(action)
        }

        checkNetworkConnection()

        binding.apply {
            rvContactList.adapter = adapter
            edtSearch.doOnTextChanged { text, _, _, _ ->
                if (text != null) {
                    if (text.isNotEmpty()) {
                        viewModel?.isSearching = true
                        searchedQuery = text.toString().trim()
                        adapter.filterData(searchedQuery)
                    } else {
                        viewModel?.isSearching = false
                        adapter.submitList(viewModel?.userDetail?.value)
                        return@doOnTextChanged
                    }
                }
            }
            edtSearch.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) KeyboardHelper.setupKeyboardHiding(v, requireActivity())
            }
        }

        viewModel.userDetail.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.filterableList = (it)
        }

        binding.rvContactList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition == totalItemCount - 1 && viewModel.currentPage.value!! < viewModel.totalPage.value!! && !viewModel.isSearching) {
                    viewModel.getUsers()
                    Log.d("Page: ", "${viewModel.currentPage.value}")
                }
            }
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {

                    val user = adapter.getItem(position)
                    val builder = AlertDialog.Builder(binding.root.context)
                    builder.setMessage("Do you want to Delete this log?")
                    builder.setTitle("Alert !")
                    builder.setCancelable(false)
                    builder.setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteUser(user)
                    }
                    builder.setNegativeButton("No") { dialog, _ ->
                        dialog.cancel()
                        adapter.notifyItemChanged(position)
                    }
                    val alertDialog = builder.create()
                    alertDialog.show()
                } else {
                    adapter.notifyItemChanged(position)
                }
            }

        }

        viewModel.isUserDeleted.observe(viewLifecycleOwner) {
            Toast.makeText(binding.root.context, "User Deleted Successfully", Toast.LENGTH_SHORT)
                .show()
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvContactList)

    }

    private fun checkNetworkConnection() {
        if (!NetworkUtil.isNetworkConnected) showInternetConnectionLost()
    }

    private fun showInternetConnectionLost() {

        try {
            val dialog = AlertDialog.Builder(
                requireActivity(),
                android.R.style.Theme_Material_Light_Dialog_Alert
            ).create()

            dialog.setTitle("No Internet Connection")
            dialog.setMessage("Please make sure your Wi-Fi or mobile data is turned on, then try again.")
            dialog.setCancelable(false)
            dialog.setButton(
                DialogInterface.BUTTON_POSITIVE,
                "OK",
                DialogInterface.OnClickListener { _, _ -> })
            dialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                "Retry",
                DialogInterface.OnClickListener { _, _ ->

                    val hThread = HandlerThread("connectivity")
                    hThread.start()
                    Handler(hThread.looper).post {
                        if (ConnectionManager.hasInternetConnected(requireActivity())) {
                            Handler(Looper.getMainLooper()).post {
                                dialog.dismiss()
                            }
                        } else {
                            Handler(Looper.getMainLooper()).postDelayed(
                                { showInternetConnectionLost() },
                                500
                            )
                        }
                    }
                })
            dialog.show()
        } catch (e: Exception) {
            Log.e(classTag, e.toString())
        }
    }

}
