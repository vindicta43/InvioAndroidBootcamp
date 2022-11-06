package com.alperen.bitirmeprojesi.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alperen.bitirmeprojesi.databinding.FragmentHomeBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.viewmodel.MainViewModel
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickedCallback {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var searchList = arrayListOf<Food>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.etSearch.clearFocus()
        viewModel.getFoods()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            viewModel.foodList.observe(viewLifecycleOwner) {
                rwHome.apply {
                    adapter = HomeRecyclerViewAdapter(it, this@HomeFragment)
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }

            etSearch.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean = false

                override fun onQueryTextChange(p0: String?): Boolean {
                    val temp = viewModel.foodList.value?.filter {
                        it.yemek_adi.lowercase().contains(p0?.lowercase().toString())
                    }

                    if (!temp.isNullOrEmpty()) {
                        rwHome.adapter =  HomeRecyclerViewAdapter(temp, this@HomeFragment)
                        rwHome.adapter?.notifyDataSetChanged()
                    }
                    return false
                }
            })
        }
    }

    override fun onItemClick(food: Food) {
        val cartFood = viewModel.toCartFood(food)
        val action = HomeFragmentDirections.homeFragmentToProductDetailActivity(cartFood)
        findNavController().navigate(action)
    }

    override fun onItemClick(food: CartFood) {}
}