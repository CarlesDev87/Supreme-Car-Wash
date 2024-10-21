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
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_vehiculos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehiculoAdapter.ViewHolder, position: Int) {
        val vehiculo = vehiculos[position]
        val isSelected = position == selectedItemPosition

        holder.bind(vehiculo, isSelected)
    }

    override fun getItemCount(): Int = vehiculos.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemVehiculosBinding.bind(view)

        fun bind(vehiculo: VehiculoResponse, isSelected: Boolean) {
            with(binding) {
                txtMarcaModelo.text = "${vehiculo.marca} ${vehiculo.modelo}".uppercase()
                txtMatricula.text = vehiculo.matricula
                radioButton.isChecked = isSelected

                root.setOnClickListener {
                    val previousItemPosition = selectedItemPosition
                    selectedItemPosition = adapterPosition

                    notifyItemChanged(previousItemPosition) // Notifica el cambio del ítem previamente seleccionado
                    notifyItemChanged(selectedItemPosition) // Notifica el cambio del nuevo ítem seleccionado

                    listener.onClick(vehiculo)

                }

                radioButton.setOnClickListener {
                    val previousItemPosition = selectedItemPosition
                    selectedItemPosition = adapterPosition

                    notifyItemChanged(previousItemPosition)
                    notifyItemChanged(selectedItemPosition)

                    listener.onClick(vehiculo)
                }
            }
        }
    }
}
