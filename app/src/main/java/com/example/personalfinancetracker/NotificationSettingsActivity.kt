package com.example.personalfinancetracker

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancetracker.databinding.ActivityNotificationSettingsBinding
import com.example.personalfinancetracker.utils.SharedPrefsHelper

class NotificationSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationSettingsBinding
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    private val CHANNEL_ID = "budget_alerts_channel"
    private val NOTIFICATION_ID = 1
    private val REMINDER_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefsHelper = SharedPrefsHelper(this)
        createNotificationChannel()

        binding.switchBudgetAlerts.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                scheduleBudgetAlert()
            } else {
                cancelBudgetAlert()
            }
        }

        binding.switchDailyReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                scheduleDailyReminder()
            } else {
                cancelDailyReminder()
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Budget Alerts"
            val descriptionText = "Notifications for budget alerts and reminders"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun scheduleBudgetAlert() {
        // TODO: Schedule notification when budget is nearing or exceeded
        Toast.makeText(this, "Budget alert enabled (not fully implemented)", Toast.LENGTH_SHORT).show()
    }

    private fun cancelBudgetAlert() {
        // TODO: Cancel budget alert notifications
        Toast.makeText(this, "Budget alert disabled (not fully implemented)", Toast.LENGTH_SHORT).show()
    }

    private fun scheduleDailyReminder() {
        // TODO: Schedule daily reminder notification to record expenses
        Toast.makeText(this, "Daily reminder enabled (not fully implemented)", Toast.LENGTH_SHORT).show()
    }

    private fun cancelDailyReminder() {
        // TODO: Cancel daily reminder notifications
        Toast.makeText(this, "Daily reminder disabled (not fully implemented)", Toast.LENGTH_SHORT).show()
    }
}
