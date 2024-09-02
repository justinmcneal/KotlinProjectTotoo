package com.example.kotlinproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Intent
import android.text.InputFilter
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class LogIn : AppCompatActivity() {
    private lateinit var etClickableView: TextView
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var etPassword: EditText
    private lateinit var etUsername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Ensure this is supported in your project or remove if not needed
        setContentView(R.layout.login)

        etClickableView = findViewById(R.id.etClickableView)
        btnLogin = findViewById(R.id.btnLogin)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        etPassword = findViewById(R.id.etPassword)
        etUsername = findViewById(R.id.etUsername)

        // Input filter to remove whitespaces
        val noWhiteSpaceFilter = InputFilter { source, _, _, _, _, _ ->
            if (source.toString().contains(" ")) "" else null
        }
        etPassword.filters = arrayOf(noWhiteSpaceFilter)

        setupPasswordToggle(etPassword)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            when {
                username.isEmpty() -> showToast("Please Enter your Username")
                password.isEmpty() -> showToast("Please Enter your Password")
                else -> {
                    showToast("Log in Successfully.")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Log.d("LogIn", "Navigating to MainActivity")
                }
            }
        }

        etClickableView.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
    }

    private fun setupPasswordToggle(editText: EditText) {
        var isPasswordVisible = false
        val eyeOpenDrawable = ContextCompat.getDrawable(this, R.drawable.eyeiconview)
        val eyeClosedDrawable = ContextCompat.getDrawable(this, R.drawable.eyeiconhide)

        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeClosedDrawable, null)

        editText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = editText.compoundDrawables[2]
                if (drawableEnd != null && event.rawX >= (editText.right - drawableEnd.bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    editText.transformationMethod = if (isPasswordVisible) {
                        HideReturnsTransformationMethod.getInstance()
                    } else {
                        PasswordTransformationMethod.getInstance()
                    }
                    editText.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        if (isPasswordVisible) eyeOpenDrawable else eyeClosedDrawable, null)
                    editText.setSelection(editText.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
