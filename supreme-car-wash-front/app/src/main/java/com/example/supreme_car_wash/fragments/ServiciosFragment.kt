package com.example.supreme_car_wash.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.activities.NuevoVehiculoActivity
import com.example.supreme_car_wash.adapters.LavadoAdapter
import com.example.supreme_car_wash.adapters.OnClickListenerLavado
import com.example.supreme_car_wash.adapters.OnClickListenerVehiculo
import com.example.supreme_car_wash.adapters.VehiculoAdapter
import com.example.supreme_car_wash.databinding.FragmentMainBinding
import com.example.supreme_car_wash.databinding.FragmentServiciosBinding
import com.example.supreme_car_wash.responses.ClienteResponse
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

private const val ARG_CLIENTE = "cliente"

class ServiciosFragment : Fragment(), OnClickListenerLavado, OnClickListenerVehiculo {


    private lateinit var lavados: List<LavadoResponse>
    private lateinit var binding: FragmentServiciosBinding
    private lateinit var lavadoAdapter: LavadoAdapter
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var vehiculos: List<VehiculoResponse>
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var cliente: ClienteResponse
    private lateinit var vehiculoAdapter: VehiculoAdapter
    private lateinit var vehiculo: VehiculoResponse


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

        //METODOS DE LLAMADA A SERVICIOS BACKEND PARA OBTENER LOS DATOS DE CLIENTE Y VEHICULOS
        getCliente(idCliente)
        getVehiculos(idVehiculo)

        //METODOS PARA CONTROLAR EVENTOS DE LA TOOLBAR Y SELECCION DE VEHICULOS
       // botonSeleccionVehiculo()


        return binding.root
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLavados(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val llamada = getRetrofit().create(APIService::class.java).getLavados(query)
                lavados = llamada.body()!!
                lavadoAdapter = LavadoAdapter(lavados,this@ServiciosFragment)
                linearLayout = LinearLayoutManager(context)

                withContext(Dispatchers.Main) {
                    binding.recyclerLavados.apply {
                        layoutManager = linearLayout
                        adapter = lavadoAdapter
                        addItemDecoration(itemDecoration)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Mostrar un mensaje de error al usuario
                    Toast.makeText(
                        requireContext(),
                        "No existen lavados para este coche",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun getVehiculos(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val llamada = getRetrofit().create(APIService::class.java).getVehiculos(query)
                vehiculos = llamada.body()!!
                withContext(Dispatchers.Main) {
                    if (vehiculos.isNotEmpty()) {
                        vehiculo = vehiculos[0]
                        crearToolbar()
                    }
                }
            } catch (e: NullPointerException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "No existen vehículos para este cliente", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }


    //VOY A OBTENER EL CLIENTE QUE HACE LOGIN MEDIANTE EL ID
    private fun getCliente(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getCliente(query)
            cliente = llamada.body()!!
        }
    }

    private fun getVehiculo(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getVehiculo(id)
            vehiculo = llamada.body()!!
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(cliente: ClienteResponse) =
            ServiciosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, cliente)
                }
            }
    }

    override fun onClick(vehiculo: VehiculoResponse) {
        this.vehiculo = vehiculo
        getLavados("/lavados/${vehiculo.id}")
        crearToolbar()

    }

    private fun crearToolbar() {

        val toolbar = binding.toolbar
        toolbar.title = "Coche: ${vehiculo.marca} ${vehiculo.modelo} ${vehiculo.matricula}"
        toolbar.setTitleTextColor(resources.getColor(R.color.white, null))

        toolbar.setNavigationOnClickListener {

            val dialogo = layoutInflater.inflate(R.layout.dialogo_seleccion_vehiculo, null)
            val recycler: RecyclerView = dialogo.findViewById(R.id.recyclerVehiculos)

            recycler.layoutManager = LinearLayoutManager(context)
            vehiculoAdapter = VehiculoAdapter(vehiculos, this@ServiciosFragment)
            recycler.adapter = vehiculoAdapter
            recycler.addItemDecoration(itemDecoration)

            MaterialAlertDialogBuilder(requireContext(), R.style.CustomDialogTheme)
                .setTitle("Vehículos de: " + cliente.nombre + " " + cliente.apellido)
                .setView(dialogo)
                .setPositiveButton("Selecciona tu vehículo y pulsa aquí") { dialog, which ->
                    // Respond to positive button press
                    onClick(vehiculo)
                    Toast.makeText(context, "Seleccionado: ${vehiculo.marca} ${vehiculo.modelo} ${vehiculo.matricula}", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onClick(lavado: LavadoResponse) {}
}