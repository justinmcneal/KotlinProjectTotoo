package com.example.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogInActivity : AppCompatActivity() {

    val etClickableView: TextView = findViewById(R.id.etClickableView)
    val btnLogin = findViewById<Button>(R.id.btnLogin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)

        btnLogin.setOnClickListener { //clickable btn
            val intent = Intent(this, HomePage::class.java)
        }
        etClickableView.setOnClickListener{ //clickable text view
            val intent = Intent(this, SignupActivity::class.java)
        }
    }
}