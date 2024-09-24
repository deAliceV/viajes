package com.example.misviajesdimont

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetalleViajeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_viaje)

        val destino = intent.getStringExtra("destino")
        val fechaInicio = intent.getStringExtra("fechaInicio")
        val fechaFin = intent.getStringExtra("fechaFin")

        val tvDestino = findViewById<TextView>(R.id.tvDetalleDestino)
        val tvFechaInicio = findViewById<TextView>(R.id.tvDetalleFechaInicio)
        val tvFechaFin = findViewById<TextView>(R.id.tvDetalleFechaFin)
        val btnEliminar = findViewById<Button>(R.id.btnEliminarViaje)

        // Mostrar los datos del viaje
        tvDestino.text = "Destino: $destino"
        tvFechaInicio.text = "Fecha de Inicio: $fechaInicio"
        tvFechaFin.text = "Fecha de Fin: $fechaFin"

        // Acción para eliminar el viaje
        btnEliminar.setOnClickListener {
            // Volver a la MainActivity con el dato a eliminar
            val intent = Intent()
            intent.putExtra("destinoEliminar", destino)
            setResult(RESULT_OK, intent)
            finish() // Cerrar esta actividad
        }
    }
}