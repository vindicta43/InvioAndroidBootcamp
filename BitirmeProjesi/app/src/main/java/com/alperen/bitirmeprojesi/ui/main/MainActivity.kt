package com.alperen.bitirmeprojesi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alperen.bitirmeprojesi.R
import com.alperen.bitirmeprojesi.databinding.ActivityMainBinding
import com.alperen.bitirmeprojesi.model.CartFood
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        val cartFoodList = arrayListOf<CartFood>()
        private var privateBinding: ActivityMainBinding? = null

        fun setBadge() {
            // Orders item badge
            var orderAmount = 0
            cartFoodList.forEach { orderAmount += it.yemek_siparis_adet }

            val badge = privateBinding?.bottomNav?.getOrCreateBadge(R.id.ordersFragment)
            if (orderAmount == 0) {
                badge?.isVisible = false
            } else {
                badge?.isVisible = true
                badge?.number = orderAmount
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        privateBinding = binding
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        setBadge()
    }
}