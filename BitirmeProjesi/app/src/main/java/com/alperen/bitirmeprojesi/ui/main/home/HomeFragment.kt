package com.alperen.bitirmeprojesi.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentHomeBinding
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.network.FoodDao
import com.alperen.bitirmeprojesi.network.RetrofitClient
import com.alperen.bitirmeprojesi.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            CoroutineScope(Dispatchers.Main).launch {
                val service =
                    RetrofitClient.getInstance(Constants.BASE_URL)?.create(FoodDao::class.java)

                val yemekler = service?.getFoods()!!.yemekler
                rwHome.apply {
                    adapter = HomeRecyclerViewAdapter(yemekler)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }
}