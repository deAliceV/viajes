package com.example.misviajesdimont

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class ViajesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viajes)

        val etDestino = findViewById<EditText>(R.id.editTextDestino)
        val tvFechaInicio = findViewById<TextView>(R.id.tvFechaInicio)
        val tvFechaFin = findViewById<TextView>(R.id.tvFechaFin)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Configurar el click en el TextView de fecha de inicio
        tvFechaInicio.setOnClickListener {
            mostrarDatePickerDialog(tvFechaInicio)
        }

        // Configurar el click en el TextView de fecha de fin
        tvFechaFin.setOnClickListener {
            mostrarDatePickerDialog(tvFechaFin)
        }
        btnGuardar.setOnClickListener {
            val destino = etDestino.text.toString()
            val fechaInicio = tvFechaInicio.text.toString()
            val fechaFin = tvFechaFin.text.toString()

            if (destino.isNotEmpty() && fechaInicio.isNotEmpty() && fechaFin.isNotEmpty()) {
                val intent = Intent()
                val sharedPreferences = getSharedPreferences("ViajesPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("destino", destino)
                editor.putString("fechaInicio", fechaInicio)
                editor.putString("fechaFin", fechaFin)

                editor.apply()
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun mostrarDatePickerDialog(textView: TextView) {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            // Mostrar la fecha seleccionada en el TextView
            val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
            textView.text = fechaSeleccionada
        }, anio, mes, dia)

        datePickerDialog.show()
    }
}