package com.alperen.bitirmeprojesi.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            tvSignUp1.setOnClickListener{ signUp() }
            tvSignUp2.setOnClickListener{ signUp() }
            tvForgotPassword.setOnClickListener { findNavController().navigate(R.id.signInFragment_to_forgotPasswordFragment) }
            btnSignIn.setOnClickListener { findNavController().navigate(R.id.signInFragment_to_mainActivity) }
        }
    }

    private fun signUp() = findNavController().navigate(R.id.signInFragment_to_signUpFragment)
}