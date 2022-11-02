package com.alperen.bitirmeprojesi.ui.auth

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

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
                    findNavController().navigate(R.id.onboardingFragment_to_signInFragment)
                }

                override fun onAnimationStart(p0: Animator?) = Unit

                override fun onAnimationCancel(p0: Animator?) = Unit

                override fun onAnimationRepeat(p0: Animator?) = Unit
            })
        }
    }
}