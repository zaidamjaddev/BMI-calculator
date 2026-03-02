package com.example.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etAge = findViewById<TextInputEditText>(R.id.etAge)
        val etHeight = findViewById<TextInputEditText>(R.id.etHeight)
        val etWeight = findViewById<TextInputEditText>(R.id.etWeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        val cvResult = findViewById<CardView>(R.id.cvResult)
        val tvUserDetails = findViewById<TextView>(R.id.tvUserDetails)
        val tvBMIValue = findViewById<TextView>(R.id.tvBMIValue)
        val tvBMIStatus = findViewById<TextView>(R.id.tvBMIStatus)

        btnCalculate.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val heightStr = etHeight.text.toString()
            val weightStr = etWeight.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val heightCm = heightStr.toDouble()
                val weightKg = weightStr.toDouble()

                if (heightCm > 0) {
                    val heightM = heightCm / 100.0
                    val bmi = weightKg / (heightM * heightM)
                    
                    val bmiFormatted = String.format("%.2f", bmi)
                    
                    tvUserDetails.text = "Name: $name, Age: $age"
                    tvBMIValue.text = "BMI: $bmiFormatted"
                    
                    val (status, color) = when {
                        bmi < 18.5 -> "Underweight" to Color.BLUE
                        bmi < 25.0 -> "Normal" to Color.GREEN
                        else -> "Overweight" to Color.RED
                    }
                    
                    tvBMIStatus.text = status
                    tvBMIStatus.setTextColor(color)
                    
                    cvResult.visibility = View.VISIBLE
                }
            }
        }
    }
}
