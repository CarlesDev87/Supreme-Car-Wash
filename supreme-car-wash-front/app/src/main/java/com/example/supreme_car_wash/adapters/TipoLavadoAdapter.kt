package com.example.supreme_car_wash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ItemLavadosBinding
import com.example.supreme_car_wash.responses.LavadoResponse

class TipoLavadoAdapter (private val  tipoLavados: List<LavadoResponse>,
                         private val listener : OnClickListenerTipoLavado) : RecyclerView.Adapter<TipoLavadoAdapter.ViewHolder>(){

    private lateinit var context: Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TipoLavadoAdapter.ViewHolder {
       context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_lavados, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipoLavadoAdapter.ViewHolder, position: Int) {
        val tipoLavado = tipoLavados[position]

        val fondo = when (tipoLavado.tipoLavado) {
            "Normal" -> R.drawable.lavadonormal
            "Integral" -> R.drawable.lavadointegral
            "Deluxe" -> R.drawable.lavadosupreme
            else -> R.drawable.lavadonormal
        }

        with(holder){
            binding.txtTipoLavado.text = tipoLavado.tipoLavado.uppercase()
            binding.constraintLavado.background = (ContextCompat.getDrawable(context, fondo))
            setListener(tipoLavado)
        }
    }

    override fun getItemCount(): Int = tipoLavados.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemLavadosBinding.bind(view)

        fun setListener(tipoLavado: LavadoResponse){
            binding.root.setOnClickListener{
                listener.onClick(tipoLavado)
            }
        }
    }
}