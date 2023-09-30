package com.example.corazonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn2: Button = findViewById(R.id.crear_cuenta)
        btn2.setOnClickListener{
            val intent: Intent = Intent(this, Registro_User::class.java)
            startActivity(intent)
        }

        val correoUser = findViewById<EditText>(R.id.ingre_Correo)
        val correoUser2 = findViewById<EditText>(R.id.ingre_Correo)
        val passwordUser = findViewById<EditText>(R.id.ingre_Contrasena)

        val iniciarSesion = findViewById<Button>(R.id.seccion_ir)
        iniciarSesion.setOnClickListener {
            val email = correoUser.text.toString()
            val password = passwordUser.text.toString()

            iniciarSesion(email, password)
        }
    }

    private fun iniciarSesion(email: String, password: String) {
        val directorioExterno = getExternalFilesDir(null)
        if (directorioExterno == null) {
            Toast.makeText(this, "No se puede acceder al almacenamiento interno", Toast.LENGTH_SHORT).show()
            return
        }

        val nombreArchivo = "base_dato.txt"
        val archivo = File(directorioExterno, nombreArchivo)

        try {
            val fileReader = FileReader(archivo)
            val bufferedReader = BufferedReader(fileReader)

            var line: String? = bufferedReader.readLine()
            var validCredentials = false

            while (line != null) {
                val fieldNameValue = line.split(":")
                if (fieldNameValue.size == 2) {
                    val fieldName = fieldNameValue[0].trim()
                    val fieldValue = fieldNameValue[1].trim()

                    when (fieldName) {
                        "Email" -> {
                            val storedEmail = fieldValue
                            val storedPasswordLine = bufferedReader.readLine()
                            val storedPasswordValue = storedPasswordLine?.split(":")?.getOrNull(1)?.trim()

                            if (email == storedEmail && password == storedPasswordValue) {
                                validCredentials = true
                                break
                            }
                        }
                    }
                }

                line = bufferedReader.readLine()
            }

            bufferedReader.close()
            fileReader.close()

            if (validCredentials) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, Menu_Principal::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                // Manejar el caso de credenciales inválidas, mostrar un mensaje de error, etc.
            }

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show()
        }
    }

}

