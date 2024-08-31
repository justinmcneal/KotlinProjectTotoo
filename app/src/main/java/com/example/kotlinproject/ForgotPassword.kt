package com.example.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ForgotPassword : AppCompatActivity() {

    val btnConfirm = findViewById<Button>(R.id.btnConfirm)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)

        btnConfirm.setOnClickListener { //ConfirmBtn
            val intent = Intent(this, LogInActivity::class.java)
        }
    }
}