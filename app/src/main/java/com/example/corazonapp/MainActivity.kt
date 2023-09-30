package com.example.corazonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.ir_login)
        btn.setOnClickListener{
            val intent: Intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val bn: Button = findViewById(R.id.ir_registrar)
        bn.setOnClickListener{
            val intent: Intent = Intent(this, Registro_User::class.java)
            startActivity(intent)
        }
    }
}