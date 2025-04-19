package com.example.personalfinancetracker.model

import java.util.Date

data class Transaction(
    var id: Long = 0,
    var title: String,
    var amount: Double,
    var category: String,
    var date: Date,
    var isIncome: Boolean
)
