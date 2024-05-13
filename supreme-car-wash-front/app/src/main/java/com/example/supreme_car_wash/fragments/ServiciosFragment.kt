package com.example.supreme_car_wash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.adapters.LavadoAdapter
import com.example.supreme_car_wash.adapters.OnClickListenerLavado
import com.example.supreme_car_wash.databinding.FragmentMainBinding
import com.example.supreme_car_wash.databinding.FragmentServiciosBinding
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiciosFragment : Fragment(), OnClickListenerLavado {


    private lateinit var lavados: List<LavadoResponse>
    private lateinit var binding: FragmentServiciosBinding
    private lateinit var lavadoAdapter: LavadoAdapter
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var vehiculos: List<VehiculoResponse>
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServiciosBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        lavados = emptyList()
        vehiculos = emptyList()
        itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)

        getLavados("/lavados", "/vehiculos")

        return binding.root
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLavados(query: String, query2: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getLavados(query)
            lavados = llamada.body()!!
            val llamada2 = getRetrofit().create(APIService::class.java).getVehiculos(query2)
            vehiculos = llamada2.body()!!
            lavadoAdapter = LavadoAdapter(lavados, vehiculos, this@ServiciosFragment)
            linearLayout = LinearLayoutManager(context)


            val tipoLavados = lavados.map { it.tipoLavado }




            withContext(Dispatchers.Main) {
                binding.recyclerLavados.apply {
                    layoutManager = linearLayout
                    adapter = lavadoAdapter
                    addItemDecoration(itemDecoration)
                }
            }
        }
    }

    override fun onClick(lavado: LavadoResponse) {}

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ServiciosFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}