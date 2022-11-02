package com.alperen.bitirmeprojesi.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentHomeBinding
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.network.FoodDao
import com.alperen.bitirmeprojesi.network.RetrofitClient
import com.alperen.bitirmeprojesi.ui.viewmodel.BaseViewModel
import com.alperen.bitirmeprojesi.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
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
                    adapter = HomeRecyclerViewAdapter(it)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }
}