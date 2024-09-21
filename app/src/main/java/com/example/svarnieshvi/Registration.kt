package com.example.svarnieshvi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)


        val authComplete: Button = findViewById(R.id.authCompleteBtn)
        val authButton:TextView = findViewById(R.id.authButton)

        authComplete.setOnClickListener {
            val intent = Intent(this, Authorization::class.java)
            startActivity(intent)
        }

        authButton.setOnClickListener {
            val intent = Intent(this, Authorization::class.java)
            startActivity(intent)
        }
    }
}
