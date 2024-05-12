package com.example.supreme_car_wash.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.adapters.LavadoAdapter
import com.example.supreme_car_wash.adapters.OnClickListenerLavado
import com.example.supreme_car_wash.databinding.FragmentMainBinding
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(), OnClickListenerLavado {

    private lateinit var lavados: List<LavadoResponse>
    private lateinit var binding: FragmentMainBinding
    private lateinit var lavadoAdapter: LavadoAdapter
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var vehiculos: List<VehiculoResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        lavados = emptyList()
        vehiculos = emptyList()

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
            lavadoAdapter = LavadoAdapter(lavados, vehiculos, this@MainFragment)
            linearLayout = LinearLayoutManager(context)

            withContext(Dispatchers.Main) {
                binding.recyclerLavados.apply {
                    layoutManager = linearLayout
                    adapter = lavadoAdapter
                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(lavado: LavadoResponse) {

    }
}