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
import com.alperen.bitirmeprojesi.databinding.FragmentForgotPasswordBinding
import com.alperen.bitirmeprojesi.ui.viewmodel.AuthViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater)
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
        }
    }

    fun forgotPassword() {
        with(binding) {
            if (!etEmail.text.isNullOrEmpty()) {
                viewModel.forgotPassword(etEmail.text.toString().trim(),
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
                })
            } else {
                AppUtils.createDialog(requireContext(), AppUtils.FILL_EMPTY_SPACES)
            }
        }
    }

    fun backToLogin() = findNavController().popBackStack()
}