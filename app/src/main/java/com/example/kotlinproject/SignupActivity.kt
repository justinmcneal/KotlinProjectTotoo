package com.example.kotlinproject

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    private lateinit var tvLogin: TextView

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
        tvLogin = findViewById(R.id.tvLogin)

        leftDrawablePassword = etPassword.compoundDrawables[0]
        leftDrawableConfirmPassword = etConPassword.compoundDrawables[0]

        val noWhiteSpaceFilter = InputFilter { source, _, _, _, _, _ ->
            if (source.contains(" ")) "" else source
        }

        etPassword.filters = arrayOf(noWhiteSpaceFilter)
        etConPassword.filters = arrayOf(noWhiteSpaceFilter)

        etPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val rightDrawable = etPassword.compoundDrawables[2]
                if (rightDrawable != null && event.rawX >= (etPassword.right - rightDrawable.bounds.width())) {
                    togglePasswordVisibility(etPassword)
                    return@setOnTouchListener true
                }
            }
            false
        }

        etConPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val rightDrawable = etConPassword.compoundDrawables[2]
                if (rightDrawable != null && event.rawX >= (etConPassword.right - rightDrawable.bounds.width())) {
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
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LogIn::class.java))
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
