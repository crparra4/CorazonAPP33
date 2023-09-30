package com.example.corazonapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter
import java.io.IOException


class Registro_User : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
                guardarDatosEnArchivo(nombreUser, email, password, numero)
            }
        }
    }

    private fun guardarDatosEnArchivo(nombreUsuario: String, email: String, password: String, numero: String) {
        val directorioExterno = getExternalFilesDir(null)
        if (directorioExterno == null) {
            Toast.makeText(this, "No se puede acceder al almacenamiento interno", Toast.LENGTH_SHORT).show()
            return
        }

        val nombreArchivo = "base_dato.txt"
        val archivo = File(directorioExterno, nombreArchivo)

        try {
            val fileWriter = FileWriter(archivo, true)
            val bufferedWriter = BufferedWriter(fileWriter)

            bufferedWriter.write("Nombre de usuario: $nombreUsuario")
            bufferedWriter.newLine()
            bufferedWriter.write("Email: $email")
            bufferedWriter.newLine()
            bufferedWriter.write("Contraseya: $password")
            bufferedWriter.newLine()
            bufferedWriter.write("Número: $numero")
            bufferedWriter.newLine()

            bufferedWriter.close()
            fileWriter.close()
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
        }
    }
}
