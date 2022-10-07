package com.alperen.odev4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alperen.odev4.R
import com.alperen.odev4.databinding.FragmentBBinding

class BFragment : Fragment() {
    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btnGoY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.b_to_y)
        }
    }
}