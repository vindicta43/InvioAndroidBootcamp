package com.alperen.bitirmeprojesi.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentSignInBinding
import com.alperen.bitirmeprojesi.ui.viewmodel.AuthViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        binding.instance = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            etEmail.addTextChangedListener {
                if (etEmail.text.isNullOrEmpty())
                    AppUtils.setError(textLayoutEmail, AppUtils.FIELD_MSG)
                else
                    AppUtils.setError(textLayoutEmail, null)
            }

            etPassword.addTextChangedListener {
                if (etPassword.text.isNullOrEmpty())
                    AppUtils.setError(textLayoutPassword, AppUtils.FIELD_MSG)
                else
                    AppUtils.setError(textLayoutPassword, null)
            }
        }
    }

    fun signIn() {
        with(binding) {
            if (!etEmail.text.isNullOrEmpty() && !etPassword.text.isNullOrEmpty()) {
                viewModel.signIn(
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    object : IAuthCallback {
                        override fun onProgress() {
                            progress.visibility = View.VISIBLE
                        }

                        override fun onFinished(resultCode: Int, msg: String?) {
                            progress.visibility = View.GONE
                            when (resultCode) {
                                AppUtils.RESULT_OK -> {
                                    findNavController().navigate(R.id.signInFragment_to_mainActivity)
                                    requireActivity().finish()
                                }
                                AppUtils.RESULT_ERROR -> {
                                    AppUtils.createDialog(requireContext(), msg)
                                }
                            }
                        }
                    }
                )
            } else {
                AppUtils.createDialog(requireContext(), AppUtils.FILL_EMPTY_SPACES)
            }
        }
    }

    fun signUp() = findNavController().navigate(R.id.signInFragment_to_signUpFragment)

    fun forgotPassword() = findNavController().navigate(R.id.signInFragment_to_forgotPasswordFragment)
}