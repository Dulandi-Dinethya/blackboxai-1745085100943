package com.example.personalfinancetracker

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.personalfinancetracker.databinding.ActivityBackupRestoreBinding
import com.example.personalfinancetracker.utils.SharedPrefsHelper
import java.io.File
import java.io.FileOutputStream

class BackupRestoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBackupRestoreBinding
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    private val STORAGE_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackupRestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefsHelper = SharedPrefsHelper(this)

        binding.btnBackup.setOnClickListener {
            if (checkPermission()) {
                backupData()
            } else {
                requestPermission()
            }
        }

        binding.btnRestore.setOnClickListener {
            // TODO: Implement restore functionality (e.g., file picker and read file)
            Toast.makeText(this, "Restore feature not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                backupData()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun backupData() {
        val transactions = sharedPrefsHelper.getTransactions()
        val json = sharedPrefsHelper.gson.toJson(transactions)

        val fileName = "finance_backup_${System.currentTimeMillis()}.json"
        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)

        try {
            val fos = FileOutputStream(file)
            fos.write(json.toByteArray())
            fos.close()
            Toast.makeText(this, "Backup saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Backup failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
