
package com.example.misviajesdimont

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viajeAdapter: ViajeAdapter
    private val listaViajes = mutableListOf<Viaje>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvViajes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Pasar el callback para los clics en cada item del RecyclerView
        viajeAdapter = ViajeAdapter(listaViajes) { viajeSeleccionado ->
            // Intent para abrir la actividad con los detalles del viaje seleccionado
            val intent = Intent(this, DetalleViajeActivity::class.java)
            intent.putExtra("destino", viajeSeleccionado.destino)
            intent.putExtra("fechaInicio", viajeSeleccionado.fechaInicio)
            intent.putExtra("fechaFin", viajeSeleccionado.fechaFin)
            startActivity(intent)  // Iniciar la nueva actividad
        }
        recyclerView.adapter = viajeAdapter

        val button = findViewById<Button>(R.id.btnAgregar)
        button.setOnClickListener {
            val intent = Intent(this, ViajesActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onResume() {
        super.onResume()

        // Obtener datos de SharedPreferences
        val sharedPreferences = getSharedPreferences("ViajesPrefs", Context.MODE_PRIVATE)

        val destino = sharedPreferences.getString("destino", "")
        val fechaInicio = sharedPreferences.getString("fechaInicio", "")
        val fechaFin = sharedPreferences.getString("fechaFin", "")

        if (!destino.isNullOrEmpty() && !fechaInicio.isNullOrEmpty() && !fechaFin.isNullOrEmpty()) {
            // Crear un nuevo objeto Viaje y agregarlo a la lista
            val nuevoViaje = Viaje(destino, fechaInicio, fechaFin)
            listaViajes.add(nuevoViaje)

            // Actualizar el RecyclerView
            viajeAdapter.notifyDataSetChanged()

            // Limpiar los datos de SharedPreferences para evitar que se repitan
            val editor = sharedPreferences.edit()
            editor.clear()  // Limpiar después de leer los datos
            editor.apply()
        }
    }
}
