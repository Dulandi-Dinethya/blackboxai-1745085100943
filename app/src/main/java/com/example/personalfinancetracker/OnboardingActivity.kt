package com.example.personalfinancetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancetracker.databinding.OnboardingScreenBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: OnboardingScreenBinding

    private var currentScreen = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OnboardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showScreen(currentScreen)

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showScreen(screen: Int) {
        when (screen) {
            1 -> {
                binding.root.visibility = View.VISIBLE
                setContentView(binding.root)
            }
            2 -> {
                setContentView(R.layout.onboarding_screen_2)
                findViewById<View>(R.id.btnNext2).setOnClickListener {
                    currentScreen = 3
                    showScreen(currentScreen)
                }
                findViewById<View>(R.id.btnBack2).setOnClickListener {
                    currentScreen = 1
                    showScreen(currentScreen)
                }
            }
            3 -> {
                setContentView(R.layout.onboarding_screen_3)
                findViewById<View>(R.id.btnGetStarted3).setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                findViewById<View>(R.id.btnBack3).setOnClickListener {
                    currentScreen = 2
                    showScreen(currentScreen)
                }
            }
        }
    }
}
