package com.alperen.odev4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alperen.odev4.R
import com.alperen.odev4.databinding.FragmentHomepageBinding

class HomepageFragment : Fragment() {
    private lateinit var binding: FragmentHomepageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomepageBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btnGoA.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homepage_to_a)
        }

        binding.btnGoX.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homepage_to_x)
        }
    }
}