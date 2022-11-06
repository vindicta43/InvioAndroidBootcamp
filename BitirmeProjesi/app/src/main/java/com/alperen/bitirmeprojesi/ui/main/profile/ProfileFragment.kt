package com.alperen.bitirmeprojesi.ui.main.profile

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentProfileBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.main.MainActivity
import com.alperen.bitirmeprojesi.ui.viewmodel.MainViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), ItemClickedCallback {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        binding.instance = this
        binding.userDetail = "Hello ${viewModel.authInstance.currentUser?.email}"
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            viewModel.getOrders()

            viewModel.ordersList.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    noItemFound.visibility = View.GONE
                    rwProfile.visibility = View.VISIBLE

                    rwProfile.apply {
                        adapter = ProfileRecyclerViewAdapter(it, this@ProfileFragment)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                } else {
                    noItemFound.visibility = View.VISIBLE
                    rwProfile.visibility = View.GONE
                }
            }
        }
    }

    fun logout() {

    }

    override fun onItemClick(food: CartFood) {
        viewModel.deleteOrder(food.sepet_yemek_id, food.kullanici_adi).observe(viewLifecycleOwner) {
            when (it?.success) {
                // Success
                AppUtils.RESULT_OK -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Success")
                        .setMessage("Product successfully deleted from previous orders")
                        .setNegativeButton("Okay") { _, _ -> }
                        .show()
                    val temp = ArrayList<CartFood>(viewModel.ordersList.value)
                    temp.remove(food)
                    viewModel.ordersList.value = temp
                    binding.rwProfile.adapter?.notifyDataSetChanged()
                }
                // Other situations
                else -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage(it?.message)
                        .setNegativeButton("Okay") { _, _ -> }
                        .show()
                }
            }
        }
    }

    override fun onItemClick(food: Food) {}
}