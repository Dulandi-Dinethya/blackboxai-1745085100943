package com.example.personalfinancetracker.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.personalfinancetracker.model.Transaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsHelper(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("finance_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveTransactions(transactions: List<Transaction>) {
        val json = gson.toJson(transactions)
        prefs.edit().putString("transactions", json).apply()
    }

    fun getTransactions(): List<Transaction> {
        val json = prefs.getString("transactions", null)
        return if (json != null) {
            val type = object : TypeToken<List<Transaction>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun saveBudget(budget: Double) {
        prefs.edit().putFloat("budget", budget.toFloat()).apply()
    }

    fun getBudget(): Double {
        return prefs.getFloat("budget", 0f).toDouble()
    }

    fun saveCurrency(currency: String) {
        prefs.edit().putString("currency", currency).apply()
    }

    fun getCurrency(): String? {
        return prefs.getString("currency", "LKR")
    }
}
