package com.example.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("NAME") ?: ""
        val age = intent.getStringExtra("AGE") ?: ""
        val bmiValue = intent.getDoubleExtra("BMI_VALUE", 0.0)

        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val tvBMI = findViewById<TextView>(R.id.tvBMI)
        val tvUserDetails = findViewById<TextView>(R.id.tvUserDetails)
        val btnReCalculate = findViewById<Button>(R.id.btnReCalculate)

        tvBMI.text = String.format("%.2f", bmiValue)
        tvUserDetails.text = "Name: $name\nAge: $age years"

        val (status, color) = when {
            bmiValue < 18.5 -> "Underweight" to Color.BLUE
            bmiValue < 25.0 -> "Normal" to Color.parseColor("#2E7D32") // Darker green
            else -> "Overweight" to Color.RED
        }

        tvStatus.text = status
        tvStatus.setTextColor(color)

        btnReCalculate.setOnClickListener {
            finish()
        }
    }
}
