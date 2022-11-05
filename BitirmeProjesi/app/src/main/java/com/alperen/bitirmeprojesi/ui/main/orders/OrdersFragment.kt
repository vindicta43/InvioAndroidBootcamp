package com.alperen.bitirmeprojesi.ui.main.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.bitirmeprojesi.databinding.FragmentOrdersBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.main.MainActivity
import com.alperen.bitirmeprojesi.ui.main.home.HomeFragmentDirections
import com.alperen.bitirmeprojesi.ui.viewmodel.MainViewModel
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.alperen.bitirmeprojesi.ui.main.home.HomeRecyclerViewAdapter

class OrdersFragment : Fragment(), ItemClickedCallback {
    private lateinit var binding: FragmentOrdersBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            rwOrders.apply {
                adapter = OrdersRecyclerViewAdapter(MainActivity.cartFoodList, this@OrdersFragment)
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onItemClick(food: CartFood) {
        val action = OrdersFragmentDirections.ordersFragmentToProductDetailActivity(food)
        findNavController().navigate(action)
    }

    override fun onItemClick(food: Food) {}
}