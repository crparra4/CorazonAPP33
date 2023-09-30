package com.example.corazonapp


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException


class Registro_User : AppCompatActivity() {
    private var connection = ConnectioB();
    override fun onCreate(savedInstanceState: Bundle?) {1
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_user)

        val nombreUserEditText = findViewById<EditText>(R.id.Nombre_user)
        val emailEditText = findViewById<EditText>(R.id.regis_email)
        val passwordEditText = findViewById<EditText>(R.id.regis_password)
        val numeroEditText = findViewById<EditText>(R.id.regis_number)

        val guardarButton = findViewById<Button>(R.id.regis_registrar)
        guardarButton.setOnClickListener {
            val nombreUser = nombreUserEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val numero = numeroEditText.text.toString()
            if (nombreUser.isEmpty() || email.isEmpty() || password.isEmpty() || numero.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                conexionBase(nombreUser, email, password, numero)
            }
        }
    }


    fun conexionBase(nombreUsuario: String, email: String, password: String, numero: String){
        val url = "http://192.168.68.113/android_sql/repetirUser.php"
        val queue = Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener <String>{ response ->
                Toast.makeText(this,"usuario se inserto existo",Toast.LENGTH_LONG).show()
            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros=HashMap<String,String>()
                parametros.put("nombreuser", nombreUsuario)
                parametros.put("correo", email)
                parametros.put("pass",password)
                parametros.put("numero", numero)
                return parametros
            }
        }
        queue.add(resultadoPost)
    }

}