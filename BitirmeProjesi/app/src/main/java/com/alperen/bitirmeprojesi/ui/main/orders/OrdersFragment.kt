package com.alperen.bitirmeprojesi.ui.main.orders

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alperen.bitirmeprojesi.databinding.FragmentOrdersBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.main.MainActivity
import com.alperen.bitirmeprojesi.ui.viewmodel.MainViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment(), ItemClickedCallback {
    private lateinit var binding: FragmentOrdersBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater)
        binding.instance = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    fun checkout() {
        var checkoutMsg = ""
        var checkoutQty = 0
        var checkoutPrc = 0

        if (MainActivity.cartFoodList.isNotEmpty()) {
            MainActivity.cartFoodList.forEach {
                checkoutMsg += "${it.yemek_adi} \t quantity: ${it.yemek_siparis_adet} \t total: ${it.yemek_siparis_adet * it.yemek_fiyat} ₺\n"
                checkoutQty += it.yemek_siparis_adet
                checkoutPrc += (it.yemek_siparis_adet * it.yemek_fiyat)
            }

            checkoutMsg += "\nTotal cart quantity: $checkoutQty\n" +
                    "Total cart price: $checkoutPrc ₺\n" +
                    "Do you want to purchase all products in the cart?"

            AlertDialog.Builder(requireContext())
                .setTitle("Checkout")
                .setMessage(checkoutMsg)
                .setPositiveButton("Okay") { _, _ ->
                    viewModel.checkout(MainActivity.cartFoodList).observe(viewLifecycleOwner) {
                        when (it?.success) {
                            // Success
                            AppUtils.RESULT_OK -> {
                                AlertDialog.Builder(requireContext())
                                    .setTitle("Success")
                                    .setMessage("Products purchased successfully")
                                    .setNegativeButton("Okay") { _, _ -> }
                                    .show()
                                MainActivity.cartFoodList.clear()
                                updateUi()
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
        } else {
            Snackbar.make(binding.root, "Cart is empty", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun updateUi() {
        with(binding) {
            MainActivity.setBadge()

            if (MainActivity.cartFoodList.isNotEmpty()) {
                noItemFound.visibility = View.GONE
                rwOrders.visibility = View.VISIBLE

                rwOrders.apply {
                    adapter = OrdersRecyclerViewAdapter(MainActivity.cartFoodList, this@OrdersFragment)
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
            } else {
                noItemFound.visibility = View.VISIBLE
                rwOrders.visibility = View.GONE
            }
        }
    }

    override fun onItemClick(food: CartFood) {
        val action = OrdersFragmentDirections.ordersFragmentToProductDetailActivity(food)
        findNavController().navigate(action)
    }

    override fun onItemClick(food: Food) {}
}