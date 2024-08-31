package com.example.kotlinproject


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    val tvLogin: TextView = findViewById(R.id.tvLogin)

    lateinit var etUsername : EditText
    lateinit var etEmail : EditText
    lateinit var  etPassword : EditText
    lateinit var etConPassword : EditText
    lateinit var btnSignup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        this.etUsername = findViewById(R.id.etUsername)
        this.etEmail = findViewById(R.id.etEmail)
        this.etPassword = findViewById(R.id.etPassword)
        this.etConPassword = findViewById(R.id.etConfirmPassword)
        this.btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener{
            var etUsername = etUsername.text.toString()
            var etEmail = etEmail.text.toString()
            var etPassword = etPassword.text.toString()
            var etConPassword = etConPassword.text.toString()

            if (etUsername == ""){
                Toast.makeText(this@SignupActivity,
                    "Please Enter your Username", Toast.LENGTH_SHORT).show()
            }
            else if (etEmail == ""){
                Toast.makeText(this@SignupActivity,
                    "Please Enter your Email", Toast.LENGTH_SHORT).show()
            }
            else if (etPassword == ""){
                Toast.makeText(this@SignupActivity,
                    "Please Create a Password", Toast.LENGTH_SHORT).show()
            }
            else if (etConPassword == ""){
                Toast.makeText(this@SignupActivity,
                    "Please Confirm Your Password", Toast.LENGTH_SHORT).show()
            }
            else if (etPassword != etConPassword ){
                Toast.makeText(this@SignupActivity,
                    "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@SignupActivity,
                    "Account created successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage:: class.java)
                startActivity(intent)
            }

        }
        tvLogin.setOnClickListener { //clicakble textview
            val intent = Intent(this, LogInActivity::class.java)
        }

        }
    }
