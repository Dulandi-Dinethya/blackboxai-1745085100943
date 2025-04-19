package com.example.personalfinancetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancetracker.databinding.ActivityCategorySummaryBinding
import com.example.personalfinancetracker.utils.SharedPrefsHelper

class CategorySummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategorySummaryBinding
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefsHelper = SharedPrefsHelper(this)
        displayCategorySummary()
    }

    private fun displayCategorySummary() {
        val transactions = sharedPrefsHelper.getTransactions()
        val categoryTotals = mutableMapOf<String, Double>()

        for (transaction in transactions) {
            if (!transaction.isIncome) {
                categoryTotals[transaction.category] = categoryTotals.getOrDefault(transaction.category, 0.0) + transaction.amount
            }
        }

        val summaryText = StringBuilder()
        for ((category, total) in categoryTotals) {
            summaryText.append("$category: \$${"%.2f".format(total)}\n")
        }

        if (summaryText.isEmpty()) {
            binding.tvCategorySummaryList.text = "No expenses recorded."
        } else {
            binding.tvCategorySummaryList.text = summaryText.toString()
        }
    }
}
