package com.alperen.odev5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alperen.odev5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // If user did a calculation before, it change to true
    private var isCalculated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            key0.setOnClickListener { updateText("0") }
            key1.setOnClickListener { updateText("1") }
            key2.setOnClickListener { updateText("2") }
            key3.setOnClickListener { updateText("3") }
            key4.setOnClickListener { updateText("4") }
            key5.setOnClickListener { updateText("5") }
            key6.setOnClickListener { updateText("6") }
            key7.setOnClickListener { updateText("7") }
            key8.setOnClickListener { updateText("8") }
            key9.setOnClickListener { updateText("9") }

            keyPlus.setOnClickListener { updateText("+") }
            keyBackspace.setOnClickListener { backspace() }
            keyClear.setOnClickListener { clear() }
            keyCalculate.setOnClickListener { calculate() }
        }
    }

    private fun updateText(key: String) {
        val inputText = binding.tvInput.text
        if (inputText.isNullOrEmpty() && key == "0") {
            // Empty state for first input is zero (0)
            return
        } else {
            // Cleans the result on the screen if user did a calculation before
            if(isCalculated) {
                binding.tvInput.text = ""
                isCalculated = false
            }

            if (key == "+" && inputText.contains("+")) {
                // Empty state for another plus (+)
                return
            } else {
                val text = binding.tvInput.text.toString()
                binding.tvInput.text = text + key
            }
        }
    }

    private fun backspace() {
        val inputText = binding.tvInput.text
        if (inputText.isNotEmpty()) {
            binding.tvInput.text = inputText.dropLast(1)
        }
    }

    private fun clear() {
        with(binding) {
            tvInput.text = ""
            tvPrevious.text = ""
        }
    }

    private fun calculate() {
        var sumText = 0

        if (!binding.tvInput.text.isNullOrEmpty()) {
            val expression = binding.tvInput.text.split("+").toTypedArray()
            binding.tvPrevious.text = binding.tvInput.text
            expression.forEach {
                sumText += it.toInt()
            }
            binding.tvInput.text = sumText.toString()
            isCalculated = true
        }
    }
}