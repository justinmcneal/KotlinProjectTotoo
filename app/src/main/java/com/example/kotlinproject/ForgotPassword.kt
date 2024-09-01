package com.example.kotlinproject

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputFilter
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        val eyeOpenDrawable = ContextCompat.getDrawable(this, R.drawable.eyeiconview)
        val eyeClosedDrawable = ContextCompat.getDrawable(this, R.drawable.eyeiconhide)

        val noWhiteSpaceFilter = InputFilter { source, _, _, _, _, _ ->
            if (source.contains(" ")) "" else source
        }
        etPassword.filters = arrayOf(noWhiteSpaceFilter)
        etConfirmPassword.filters = arrayOf(noWhiteSpaceFilter)

        setupPasswordToggle(etPassword, eyeOpenDrawable, eyeClosedDrawable)
        setupPasswordToggle(etConfirmPassword, eyeOpenDrawable, eyeClosedDrawable)

        btnConfirm.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }

    private fun setupPasswordToggle(editText: EditText, eyeOpenDrawable: Drawable?, eyeClosedDrawable: Drawable?) {
        var isPasswordVisible = false

        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeClosedDrawable, null)

        editText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val drawableEnd = editText.compoundDrawables[2] // Get the right drawable
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
                        editText.setSelection(editText.text.length) // Move cursor to the end
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }
}
