package com.example.misviajesdimont

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViajeAdapter(
    private val listaViajes: List<Viaje>,
    private val onItemClick: (Viaje) -> Unit  // Callback para el clic
) : RecyclerView.Adapter<ViajeAdapter.ViajeViewHolder>() {

    class ViajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinoTextView: TextView = itemView.findViewById(R.id.textViewDestino)
        val fechaInicioTextView: TextView = itemView.findViewById(R.id.textViewFechaInicio)
        val fechaFinTextView: TextView = itemView.findViewById(R.id.textViewFechaFin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViajeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viaje, parent, false)
        return ViajeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViajeViewHolder, position: Int) {
        val viaje = listaViajes[position]
        holder.destinoTextView.text = viaje.destino
        holder.fechaInicioTextView.text = viaje.fechaInicio
        holder.fechaFinTextView.text = viaje.fechaFin

        // Añadir el listener para cada item
        holder.itemView.setOnClickListener {
            onItemClick(viaje)  // Ejecutar callback cuando se hace clic en el item
        }
    }

    override fun getItemCount(): Int {
        return listaViajes.size
    }
}


