package com.example.personalfinancetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Load transactions and display summary

        binding.btnAddTransaction.setOnClickListener {
            val intent = Intent(this, AddEditTransactionActivity::class.java)
            startActivity(intent)
        }

        binding.btnBudget.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
        }

        binding.btnCategorySummary.setOnClickListener {
            val intent = Intent(this, CategorySummaryActivity::class.java)
            startActivity(intent)
        }
    }
}
