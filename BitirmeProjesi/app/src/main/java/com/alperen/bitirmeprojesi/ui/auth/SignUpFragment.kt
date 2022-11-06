package com.alperen.bitirmeprojesi.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentSignUpBinding
import com.alperen.bitirmeprojesi.ui.viewmodel.AuthViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater)
        binding.instance = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            etFullName.addTextChangedListener {
                if (etFullName.text.isNullOrEmpty())
                    AppUtils.setError(textLayoutFullName, AppUtils.FIELD_MSG)
                else
                    AppUtils.setError(textLayoutFullName, null)
            }

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

    fun signUp() {
        with(binding) {
            if (!etFullName.text.isNullOrEmpty() && !etEmail.text.isNullOrEmpty() && !etPassword.text.isNullOrEmpty()) {
                viewModel.signUp(
                    etFullName.text.toString().trim(),
                    etEmail.text.toString().trim(),
                    etPassword.text.toString(),
                    object : IAuthCallback {
                        override fun onProgress() {
                            progress.visibility = View.VISIBLE
                        }

                        override fun onFinished(resultCode: Int, msg: String?) {
                            progress.visibility = View.GONE
                            when (resultCode) {
                                AppUtils.RESULT_OK -> {
                                    AppUtils.createDialog(requireContext(), msg)
                                    findNavController().popBackStack()
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
}