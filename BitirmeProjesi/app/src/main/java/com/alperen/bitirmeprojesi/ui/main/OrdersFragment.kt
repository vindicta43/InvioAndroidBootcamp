package com.alperen.bitirmeprojesi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {
    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater)
        return binding.root
    }
}