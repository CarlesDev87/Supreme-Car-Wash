package com.example.supreme_car_wash.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.adapters.OnClickListenerTipoLavado
import com.example.supreme_car_wash.adapters.OnClickListenerVehiculo
import com.example.supreme_car_wash.adapters.TipoLavadoAdapter
import com.example.supreme_car_wash.adapters.VehiculoAdapter
import com.example.supreme_car_wash.databinding.FragmentMainBinding
import com.example.supreme_car_wash.requests.LavadoRequest
import com.example.supreme_car_wash.requests.VehiculoRequest
import com.example.supreme_car_wash.responses.ClienteResponse
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException
import java.time.LocalDate

private const val ARG_CLIENTE = "cliente"

class MainFragment : Fragment(), OnClickListenerTipoLavado, OnClickListenerVehiculo {


    private lateinit var binding: FragmentMainBinding
    private lateinit var tipoLavadoAdapter: TipoLavadoAdapter
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var tipoLavados: List<LavadoResponse>
    private lateinit var vehiculoAdapter: VehiculoAdapter
    private lateinit var vehiculos: List<VehiculoResponse>
    private lateinit var cliente: ClienteResponse
    private lateinit var vehiculo: VehiculoResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable("cliente") as ClienteResponse
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        tipoLavados = emptyList()
        vehiculos = emptyList()
        itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)

        val idCliente = "/clientes/${cliente.id.toString()}"
        val idVehiculo = "/vehiculos/${cliente.id.toString()}"

        getCliente(idCliente)
        getVehiculos(idVehiculo)
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

                    //ESTE CÓDIGO NOS AUTOAJUSTA EL ITEM DEL RECYCLER VIEW A DONDE ESTA LA VISTA
                    val snapHelper = LinearSnapHelper()
                    snapHelper.attachToRecyclerView(this)

                    this.scrollToPosition(0)
                }
            }
        }
    }

    override fun onClick(tipoLavado: LavadoResponse) {

        val dialogo = layoutInflater.inflate(R.layout.dialog_tipo_lavado, null)
        val recycler: RecyclerView = dialogo.findViewById(R.id.recyclerDialogoVehiculos)

        dialogo.findViewById<TextView>(R.id.txtDiagDescripcionTipoLavado).text =
            tipoLavado.descripcion
        dialogo.findViewById<TextView>(R.id.precioTipoLavadoDialogo).text =
            tipoLavado.precio.toString() + " €"

        recycler.layoutManager = LinearLayoutManager(context)
        vehiculoAdapter = VehiculoAdapter(vehiculos, this@MainFragment)
        recycler.adapter = vehiculoAdapter
        recycler.addItemDecoration(itemDecoration)

        MaterialAlertDialogBuilder(requireContext(), R.style.CustomDialogTheme)
            .setTitle(tipoLavado.tipoLavado)
            .setView(dialogo)
            .setPositiveButton("¿Quieres reservarlo?") { dialog, which ->
                // Respond to positive button press
                val lavadoRequest = LavadoRequest(
                    fechaCreacion = LocalDate.now().toString(),
                    tipoLavado = tipoLavado.tipoLavado,
                    descripcion = tipoLavado.descripcion,
                    precio = tipoLavado.precio,
                    estado = "Reservado",
                    vehiculo = vehiculo!!
                )

                lavadoRequest.toString()
                addLavado(lavadoRequest)

                //EMITO UN TOAST PARA DAR INFO AL CLIENTE DE QUE EL LAVADO SE HA RESERVADO O NO
                if (lavadoRequest.estado == "Reservado") {
                    Toast.makeText(context, "Lavado Reservado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        context,
                        "No se ha podido reservar el lavado",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // Respond to negative button press
                Toast.makeText(context, "Lavado Cancelado", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

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

    private fun getVehiculos(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val llamada = getRetrofit().create(APIService::class.java).getVehiculos(query)
                vehiculos = llamada.body()!!
            } catch (e: NullPointerException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "No hay vehículos disponibles", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addLavado(lavado: LavadoRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRetrofit().create(APIService::class.java).addLavado(lavado)

            } catch (e: EOFException) {
                Log.e("ERROR", e.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(cliente: ClienteResponse) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, cliente)
                }
            }
    }

    override fun onClick(vehiculo: VehiculoResponse) {
        getVehiculo(vehiculo.id)
    }


}