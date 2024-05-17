package com.example.supreme_car_wash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ItemVehiculosBinding
import com.example.supreme_car_wash.responses.VehiculoResponse

class VehiculoAdapter(
    private val vehiculos: List<VehiculoResponse>,
    private val listener: OnClickListenerVehiculo
) : RecyclerView.Adapter<VehiculoAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_vehiculos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehiculoAdapter.ViewHolder, position: Int) {
        val vehiculo = vehiculos[position]

        with(holder) {
            setListener(vehiculo)
            binding.marcaModeloCoche.text = "${vehiculo.marca} ${vehiculo.modelo}".uppercase()
            binding.matriculaCoche.text = vehiculo.matricula
        }
    }

    override fun getItemCount(): Int = vehiculos.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemVehiculosBinding.bind(view)

        fun setListener(vehiculo: VehiculoResponse) {
            binding.root.setOnClickListener {
                listener.onClick(vehiculo)
            }
        }
    }
}