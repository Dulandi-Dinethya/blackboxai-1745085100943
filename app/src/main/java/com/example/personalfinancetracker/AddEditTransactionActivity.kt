package com.example.personalfinancetracker

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancetracker.databinding.ActivityAddEditTransactionBinding
import com.example.personalfinancetracker.model.Transaction
import com.example.personalfinancetracker.utils.SharedPrefsHelper
import java.util.Calendar
import java.util.Date

class AddEditTransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditTransactionBinding
    private lateinit var sharedPrefsHelper: SharedPrefsHelper
    private var isIncome: Boolean = false // default to expense

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefsHelper = SharedPrefsHelper(this)

        // Setup category spinner
        val categories = listOf("Food", "Transport", "Bills", "Entertainment", "Others")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        // Setup income/expense toggle
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            isIncome = checkedId == binding.radioIncome.id
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val amountText = binding.etAmount.text.toString().trim()
            val category = binding.spinnerCategory.selectedItem.toString()
            val datePicker: DatePicker = binding.datePicker

            if (title.isEmpty() || amountText.isEmpty()) {
                Toast.makeText(this, "Please enter title and amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val calendar = Calendar.getInstance()
            calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            val date = calendar.time

            val transaction = Transaction(
                id = System.currentTimeMillis(),
                title = title,
                amount = amount,
                category = category,
                date = date,
                isIncome = isIncome
            )

            val transactions = sharedPrefsHelper.getTransactions().toMutableList()
            transactions.add(transaction)
            sharedPrefsHelper.saveTransactions(transactions)

            Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}
