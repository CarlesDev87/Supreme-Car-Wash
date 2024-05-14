package com.example.supreme_car_wash.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.adapters.LavadoAdapter
import com.example.supreme_car_wash.adapters.OnClickListenerLavado
import com.example.supreme_car_wash.adapters.OnClickListenerTipoLavado
import com.example.supreme_car_wash.adapters.TipoLavadoAdapter
import com.example.supreme_car_wash.databinding.FragmentMainBinding
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(), OnClickListenerTipoLavado {


    private lateinit var binding: FragmentMainBinding
    private lateinit var tipoLavadoAdapter: TipoLavadoAdapter
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var tipoLavados: List<LavadoResponse>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        tipoLavados = emptyList()
        itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)


        getTipoLavados("lavados/tipoLavado")


        return binding.root
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun getTipoLavados(query: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getLavados(query)
            tipoLavados = llamada.body()!!
            tipoLavadoAdapter = TipoLavadoAdapter(tipoLavados, this@MainFragment)
            linearLayout = LinearLayoutManager(context)

            withContext(Dispatchers.Main) {
                binding.recyclerTipoLavados.apply {
                    layoutManager = linearLayout
                    adapter = tipoLavadoAdapter
                    addItemDecoration(itemDecoration)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(tipoLavado: LavadoResponse) =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    override fun onClick(tipoLavado: LavadoResponse) {

        val dialogo = layoutInflater.inflate(R.layout.dialog_tipo_lavado, null)

        dialogo.findViewById<TextView>(R.id.txtDiagDescripcionTipoLavado).text = tipoLavado.descripcion
        dialogo.findViewById<TextView>(R.id.precioTipoLavadoDialogo).text = tipoLavado.precio.toString() + " €"
        MaterialAlertDialogBuilder(requireContext(), R.style.CustomDialogTheme)
            .setTitle(tipoLavado.tipoLavado)
            .setView(dialogo)
            .setPositiveButton("¿Quieres reservarlo?") { dialog, which ->
                // Respond to positive button press
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // Respond to negative button press
            }
            .show()


    }


}