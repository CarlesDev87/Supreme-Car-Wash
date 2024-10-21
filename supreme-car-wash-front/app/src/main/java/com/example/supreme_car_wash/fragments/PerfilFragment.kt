package com.example.supreme_car_wash.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.activities.LoginActivity
import com.example.supreme_car_wash.activities.NuevoVehiculoActivity
import com.example.supreme_car_wash.databinding.FragmentPerfilBinding
import com.example.supreme_car_wash.requests.ClienteRequest
import com.example.supreme_car_wash.responses.ClienteResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_CLIENTE = "cliente"

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        binding.etNombreCliente.setText(cliente.nombre)
        binding.etApellidoCliente.setText(cliente.apellido)
        binding.etDireccionCliente.setText(cliente.telefono)
        binding.etCorreoCliente.setText(cliente.direccion)


        binding.btnCerrarSesion.setOnClickListener {

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        }

        crearToolbar()

        return binding.root

    }

    private fun crearToolbar() {

        val toolbar = binding.toolbar
        toolbar.title = "${cliente.nombre} ${cliente.apellido}"
        toolbar.setTitleTextColor(resources.getColor(R.color.white, null))

        toolbar.setNavigationOnClickListener {
            val intent = Intent(activity, NuevoVehiculoActivity::class.java)
            intent.putExtra("cliente", cliente)
            startActivity(intent)
            Log.d("Boton Pulsado", "has pulsado el boton nuevo vehiculo")

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        @JvmStatic
        fun newInstance(cliente: ClienteResponse) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("cliente", cliente)
                }
            }
    }
}