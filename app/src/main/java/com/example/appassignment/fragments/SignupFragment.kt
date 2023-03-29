package com.example.appassignment.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.appassignment.EmailValidator
import com.example.appassignment.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Duration

class SignupFragment : Fragment() {

    private lateinit var email: TextInputEditText
    private lateinit var name: TextInputEditText
    private lateinit var contact: TextInputEditText
    private lateinit var pwd: TextInputEditText
    private lateinit var button: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var db: FirebaseFirestore

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_signup, container, false)
        button = view.findViewById(R.id.signupSubmit)
        email = view.findViewById(R.id.create_email_textEdit)
        pwd = view.findViewById(R.id.create_password_textEdit)
        name = view.findViewById(R.id.create_name_textEdit)
        contact = view.findViewById(R.id.contact_textEdit)
        checkBox = view.findViewById(R.id.term_condition_checkbox)
        db = Firebase.firestore

        button.setOnClickListener {
            if (EmailValidator.emailValidator(email, context) && checkBox.isChecked){
                val nameString: String= name.text.toString()
                val contactString: String= contact.text.toString()
                val pwdString: String= pwd.text.toString()
                if (!nameString.isEmpty() && !contactString.isEmpty() && !pwdString.isEmpty()){
                    addUser(nameString, email.text.toString(), contactString.toInt(), pwdString)
                }else{
                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(context, "Enter valid Email address", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun addUser(name:String, email:String, contact:Int, password:String){
        val user = hashMapOf(
            "Name" to name,
            "Email" to email,
            "Contact" to contact,
            "Password" to password
        )
        db.collection("Social_Users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->

                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
            }
    }




}