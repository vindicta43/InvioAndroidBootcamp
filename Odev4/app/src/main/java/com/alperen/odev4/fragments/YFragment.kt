package com.alperen.odev4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alperen.odev4.R
import com.alperen.odev4.databinding.FragmentYBinding

class YFragment : Fragment() {
    private lateinit var binding: FragmentYBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYBinding.inflate(inflater)
        return binding.root
    }
}