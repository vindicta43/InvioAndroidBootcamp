package com.alperen.bitirmeprojesi.ui.auth

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentOnboardingBinding
import com.alperen.bitirmeprojesi.ui.viewmodel.AuthViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (readFromPref()) {
            findNavController().navigate(R.id.onboardingFragment_to_signInFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            lottie.addAnimatorListener(object : AnimatorListener {
                override fun onAnimationEnd(p0: Animator?) {
                    if (viewModel.authInstance.currentUser == null)
                        findNavController().navigate(R.id.onboardingFragment_to_signInFragment)
                    else {
                        findNavController().navigate(R.id.onboardingFragment_to_mainActivity)
                        requireActivity().finish()
                    }
                }

                override fun onAnimationStart(p0: Animator?) = Unit

                override fun onAnimationCancel(p0: Animator?) = Unit

                override fun onAnimationRepeat(p0: Animator?) = Unit
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        writeToPref(false)
    }

    private fun writeToPref(bool: Boolean) {
        val prefs = requireActivity().getSharedPreferences(AppUtils.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(AppUtils.SHARED_PREF_KEY, bool).apply()
    }

    private fun readFromPref(): Boolean {
        val prefs = requireActivity().getSharedPreferences(AppUtils.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(AppUtils.SHARED_PREF_KEY, false)
    }
}