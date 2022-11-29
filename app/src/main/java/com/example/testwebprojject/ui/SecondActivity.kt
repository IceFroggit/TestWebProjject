package com.example.testwebprojject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testwebprojject.R
import com.example.testwebprojject.databinding.ActivityMainBinding
import com.example.testwebprojject.databinding.ActivitySecondBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SecondActivity : AppCompatActivity() {
   lateinit var gso: GoogleSignInOptions
   lateinit var gsc: GoogleSignInClient
    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var signOutBtn: Button

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        signOutBtn = findViewById(R.id.signout)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            name.setText(personName)
            email.setText(personEmail)
        }
        signOutBtn.setOnClickListener({ signOut() })
    }

    fun signOut() {
        gsc.signOut().addOnCompleteListener {
            finish()
            startActivity(Intent(this@SecondActivity, MainActivity::class.java))
        }
    }
}