package com.example.appassignment

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class EmailValidator {
    companion object{
        fun emailValidator(etMail: TextInputEditText, context: Context?): Boolean {

            val emailToText = etMail.text.toString()
            return !emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()
        }
    }

}