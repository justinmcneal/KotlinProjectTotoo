package com.example.kotlinproject

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SignupActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConPassword: EditText
    private lateinit var btnSignup: Button

    private var passwordVisible: Boolean = false
    private var leftDrawablePassword: Drawable? = null
    private var leftDrawableConfirmPassword: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConPassword = findViewById(R.id.etConfirmPassword)
        btnSignup = findViewById(R.id.btnSignup)

        // Store the left drawables
        leftDrawablePassword = etPassword.compoundDrawables[0]
        leftDrawableConfirmPassword = etConPassword.compoundDrawables[0]

        etPassword.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val rightDrawable = etPassword.compoundDrawables[2]
                if (event.rawX >= (etPassword.right - rightDrawable.bounds.width())) {
                    togglePasswordVisibility(etPassword)
                    return@setOnTouchListener true
                }
            }
            false
        }

        etConPassword.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val rightDrawable = etConPassword.compoundDrawables[2]
                if (event.rawX >= (etConPassword.right - rightDrawable.bounds.width())) {
                    togglePasswordVisibility(etConPassword)
                    return@setOnTouchListener true
                }
            }
            false
        }

        btnSignup.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConPassword.text.toString()

            when {
                username.isEmpty() -> showToast("Please Enter your Username")
                email.isEmpty() -> showToast("Please Enter your Email")
                password.isEmpty() -> showToast("Please Create a Password")
                confirmPassword.isEmpty() -> showToast("Please Confirm Your Password")
                password != confirmPassword -> showToast("Passwords do not match. Please try again.")
                else -> {
                    showToast("Account created successfully!")
                    startActivity(Intent(this, HomePage::class.java))
                }
            }
        }
    }

    private fun togglePasswordVisibility(editText: EditText) {
        if (passwordVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            setDrawable(editText, R.drawable.eyeiconhide)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            setDrawable(editText, R.drawable.eyeiconview)
        }
        passwordVisible = !passwordVisible
        editText.setSelection(editText.text.length)
    }

    private fun setDrawable(editText: EditText, rightDrawableId: Int) {
        val rightDrawable = ContextCompat.getDrawable(this, rightDrawableId)
        editText.setCompoundDrawablesWithIntrinsicBounds(leftDrawablePassword, null, rightDrawable, null)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
