package com.example.kotlinproject

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ForgotPassword : AppCompatActivity() {

    val btnConfirm = findViewById<Button>(R.id.btnConfirm)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val eyeOpenDrawable = ContextCompat.getDrawable(this, R.drawable.eyeiconview)
        val eyeClosedDrawable = ContextCompat.getDrawable(this, R.drawable.eyeiconhide)

        setupPasswordToggle(etPassword, eyeOpenDrawable, eyeClosedDrawable)
        setupPasswordToggle(etConfirmPassword, eyeOpenDrawable, eyeClosedDrawable)
    }

    private fun setupPasswordToggle(editText: EditText, eyeOpenDrawable: Drawable?, eyeClosedDrawable: Drawable?) {
        var isPasswordVisible = false

        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeClosedDrawable, null)

        editText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = editText.compoundDrawables[2] // Right drawable
                drawableEnd?.let {
                    if (event.rawX >= (editText.right - it.bounds.width())) {
                        isPasswordVisible = !isPasswordVisible
                        if (isPasswordVisible) {
                            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                            editText.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeOpenDrawable, null)
                        } else {
                            editText.transformationMethod = PasswordTransformationMethod.getInstance()
                            editText.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeClosedDrawable, null)
                        }
                        editText.setSelection(editText.text.length)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
        btnConfirm.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            Toast.makeText(this, "Password Change", Toast.LENGTH_SHORT).show()
        }
    }
}
