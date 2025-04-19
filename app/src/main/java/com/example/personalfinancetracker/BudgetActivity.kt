package com.example.personalfinancetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancetracker.databinding.ActivityBudgetBinding

class BudgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Load and display budget, allow user to set/update budget

        binding.btnSaveBudget.setOnClickListener {
            // TODO: Save budget to SharedPreferences
            finish()
        }
    }
}
