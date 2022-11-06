package com.alperen.bitirmeprojesi.ui.main.profile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentProfileBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.viewmodel.AuthViewModel
import com.alperen.bitirmeprojesi.ui.viewmodel.MainViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), ItemClickedCallback {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: MainViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

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
        authViewModel.logout(object : IAuthCallback {
            override fun onProgress() {}

            override fun onFinished(resultCode: Int, msg: String?) {
                when (resultCode) {
                    AppUtils.RESULT_OK -> {
                        writeToPref(true)
                        Snackbar.make(binding.root, msg!!, Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.profileFragment_to_authActivity)
                        requireActivity().finish()
                    }
                    AppUtils.RESULT_ERROR -> {
                        AppUtils.createDialog(requireContext(), msg)
                    }
                }
            }
        })
    }

    private fun writeToPref(bool: Boolean) {
        val prefs =
            requireActivity().getSharedPreferences(AppUtils.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(AppUtils.SHARED_PREF_KEY, bool).apply()
    }

    override fun onItemClick(food: CartFood) {
        AlertDialog.Builder(requireContext())
            .setTitle("gg")
            .setMessage("Do you want to remove this product from your purchased list?")
            .setPositiveButton("Okay") { _, _ ->
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
            .setNegativeButton("No") { _, _ -> }
            .show()
    }

    override fun onItemClick(food: Food) {}
}