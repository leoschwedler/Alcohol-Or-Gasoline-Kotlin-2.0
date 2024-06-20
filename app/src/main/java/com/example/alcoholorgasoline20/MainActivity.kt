package com.example.alcoholorgasoline20

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.alcoholorgasoline20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSplashScreen()
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener {
            calcularMelhorPreco()
        }
    }
    private fun setupSplashScreen() {
        Thread.sleep(3000)
        installSplashScreen()
    }

    private fun calcularMelhorPreco() {
        val priceAlcohol = binding.editTextAlcohol.text.toString()
        val priceGasoline = binding.editTextGasoline.text.toString()

        if (validadeFields(priceAlcohol, priceGasoline)) {
            val alcoholPrice = priceAlcohol.toDoubleOrNull()
            val gasolinePrice = priceGasoline.toDoubleOrNull()

            if (alcoholPrice != null && gasolinePrice != null) {
                val result = alcoholPrice / gasolinePrice
                if (result >= 0.7) {
                    binding.textResult.text = "Better to use gasoline"
                } else {
                    binding.textResult.text = "Better to use alcohol"
                }
            } else {
                binding.textResult.text = "Invalid values"
            }
        }
    }

    private fun validadeFields(priceAlcohol: String, priceGasoline: String): Boolean {
        binding.editTextAlcohol.error = null
        binding.editTextGasoline.error = null

        if (priceAlcohol.isEmpty()) {
            binding.editTextAlcohol.error = "Enter the price of alcohol"
            return false
        } else if (priceGasoline.isEmpty()) {
            binding.editTextGasoline.error = "Enter the price of gasoline"
            return false
        }

        return true
    }
}
