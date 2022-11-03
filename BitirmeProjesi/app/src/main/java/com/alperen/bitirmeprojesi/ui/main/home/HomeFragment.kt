package com.alperen.bitirmeprojesi.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.bitirmeprojesi.databinding.FragmentHomeBinding
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.viewmodel.BaseViewModel
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickedCallback {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: BaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        viewModel.getFoods()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            viewModel.foodList.observe(viewLifecycleOwner) {
                rwHome.apply {
                    adapter = HomeRecyclerViewAdapter(it, this@HomeFragment)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    override fun onItemClick(food: Food) {
        val action = HomeFragmentDirections.homeFragmentToProductDetailActivity(food)
        findNavController().navigate(action)
    }
}