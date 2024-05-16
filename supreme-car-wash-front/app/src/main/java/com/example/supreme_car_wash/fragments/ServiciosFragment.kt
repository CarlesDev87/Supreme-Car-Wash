package com.example.supreme_car_wash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.adapters.LavadoAdapter
import com.example.supreme_car_wash.adapters.OnClickListenerLavado
import com.example.supreme_car_wash.databinding.FragmentMainBinding
import com.example.supreme_car_wash.databinding.FragmentServiciosBinding
import com.example.supreme_car_wash.responses.ClienteResponse
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

private const val ARG_CLIENTE = "cliente"

class ServiciosFragment : Fragment(), OnClickListenerLavado {


    private lateinit var lavados: List<LavadoResponse>
    private lateinit var binding: FragmentServiciosBinding
    private lateinit var lavadoAdapter: LavadoAdapter
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var vehiculos: List<VehiculoResponse>
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var cliente: ClienteResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as ClienteResponse
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

        val idCliente = "/clientes/${cliente.id.toString()}"
        val idVehiculo = "/vehiculos/${cliente.id.toString()}"


        //getLavados("/lavados", "/vehiculos")
        getCliente(idCliente)
        getVehiculos(idVehiculo)


        binding.nombreUsuario.text = "${cliente.nombre} ${cliente.apellido}"


        /*
        * ESTE CODIGO HACE QUE LA TOOLBAR SE PINTE DE ROJO CUANDO SE PLIEGA LA COLLAPSING TOOLBAR Y
        * MUESTRE EL NOMBRE DEL CLIENTE COMO TITULO DE LA TOOLBAR
        * */

        val appBarLayout = binding.appbar
        val toolbar = binding.toolbar
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange
            if (abs(verticalOffset) == totalScrollRange) {
                toolbar.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.principal
                    )
                )

                //Seccion donde ponemos el nombre del cliente a la toolbar cuando se pliega la collapsing toolbar


            } else {
                toolbar.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.principal
                    )
                )

            }
        })

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


            withContext(Dispatchers.Main) {
                binding.recyclerLavados.apply {
                    layoutManager = linearLayout
                    adapter = lavadoAdapter
                    addItemDecoration(itemDecoration)
                }
            }
        }
    }

    private fun getVehiculos(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getVehiculos(query)
            vehiculos = llamada.body()!!
            withContext(Dispatchers.Main) {
                nombreVehiculos()
            }
        }
    }

    private fun nombreVehiculos() {
        for (vehiculo in vehiculos) {
            binding.vehiculoUsuario.text = "${vehiculo.marca} ${vehiculo.modelo} "
        }
    }

    //VOY A OBTENER EL CLIENTE QUE HACE LOGIN MEDIANTE EL ID
    private fun getCliente(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getCliente(query)
            cliente = llamada.body()!!
        }
    }

    override fun onClick(lavado: LavadoResponse) {}

    companion object {
        @JvmStatic
        fun newInstance(cliente: ClienteResponse) =
            ServiciosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, cliente)
                }
            }
    }
}