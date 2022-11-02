package com.alperen.bitirmeprojesi.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            tvBackToLogin.setOnClickListener { findNavController().popBackStack() }
        }
    }
}