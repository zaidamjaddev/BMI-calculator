package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

        btnCalculate.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val heightStr = etHeight.text.toString()
            val weightStr = etWeight.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val heightCm = heightStr.toDoubleOrNull() ?: 0.0
                val weightKg = weightStr.toDoubleOrNull() ?: 0.0

                if (heightCm > 0 && weightKg > 0) {
                    val heightM = heightCm / 100.0
                    val bmi = weightKg / (heightM * heightM)

                    val intent = Intent(this, ResultActivity::class.java).apply {
                        putExtra("NAME", name)
                        putExtra("AGE", age)
                        putExtra("BMI_VALUE", bmi)
                    }
                    startActivity(intent)

                    // Clear fields after navigating so they are empty when returning
                    etName.text?.clear()
                    etAge.text?.clear()
                    etHeight.text?.clear()
                    etWeight.text?.clear()
                    etName.requestFocus() // Set focus back to first field
                } else {
                    Toast.makeText(this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
