package com.example.appassignment.fragments

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appassignment.EmailValidator
import com.example.appassignment.HomeActivity
import com.example.appassignment.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class LoginFragment : Fragment() {
    private lateinit var email: TextInputEditText
    private lateinit var pwd: TextInputEditText
    private lateinit var button: TextView
    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        button = view.findViewById(R.id.loginSubmit)
        email = view.findViewById(R.id.email_textEdit)
        pwd = view.findViewById(R.id.password_textEdit)
        db = Firebase.firestore

        button.setOnClickListener {
            if (EmailValidator.emailValidator(email, context)) {
                getUser(email.text.toString(), pwd.text.toString())
            }
//
        }

        return view
    }

    private fun getUser(email: String, password: String) {
        db.collection("Social_Users")
            .whereEqualTo("Email", email)
            .whereEqualTo("Password", password)
            .get()
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                    Toast.makeText(
                        context,
                        "User not registered. Please create new account",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val intent: Intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finishAffinity()
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }


}