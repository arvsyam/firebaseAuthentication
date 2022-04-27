package com.adl.firebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        btnRegUser.setOnClickListener({
            register()
        })
    }

    private fun register() {
        auth.createUserWithEmailAndPassword(txtEmail.text.toString(),txtRegPassword.text.toString()).addOnCompleteListener{
                it ->
            if(it.isSuccessful){
                finish()
            }
        }.addOnFailureListener { exception -> Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show() }
    }
}