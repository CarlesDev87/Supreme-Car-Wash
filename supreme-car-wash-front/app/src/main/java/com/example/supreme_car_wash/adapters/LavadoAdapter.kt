package com.example.supreme_car_wash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ItemCochesBinding
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LavadoAdapter(
    private val lavados: List<LavadoResponse>,
    private val listener: OnClickListenerLavado
) : RecyclerView.Adapter<LavadoAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LavadoAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_coches, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LavadoAdapter.ViewHolder, position: Int) {

        val lavado = lavados[position]


        val color = when (lavado.tipoLavado) {
            "Normal" -> R.color.normal
            "Integral" -> R.color.integral
            "Deluxe" -> R.color.deluxe
            else -> R.color.normal
        }

        val icono = when (lavado.tipoLavado) {
            "Normal" -> R.drawable.normal
            "Integral" -> R.drawable.integral
            "Deluxe" -> R.drawable.deluxe
            else -> R.drawable.normal
        }

        with(holder) {
            setListener(lavado)
            binding.tipoLavado.text = lavado.tipoLavado.uppercase()
           // binding.precioLavado.text = "Precio"
            binding.cantidad.text = "${lavado.precio} €"
           // binding.marcaCoche.text = lavado.vehiculo.marca
           // binding.modeloCoche.text = lavado.vehiculo.modelo
          //  binding.matriculaCoche.text = lavado.vehiculo.matricula

            binding.fondoTipoLavado.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    color
                )
            )

        }
    }

    override fun getItemCount(): Int = lavados.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCochesBinding.bind(view)

        fun setListener(lavado: LavadoResponse) {
            binding.root.setOnClickListener {
                listener.onClick(lavado)
            }
        }
    }
}