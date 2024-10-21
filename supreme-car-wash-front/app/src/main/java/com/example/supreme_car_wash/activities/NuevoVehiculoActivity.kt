package com.example.supreme_car_wash.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityNuevoVehiculoBinding
import com.example.supreme_car_wash.requests.VehiculoRequest
import com.example.supreme_car_wash.responses.ClienteResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException


class NuevoVehiculoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNuevoVehiculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNuevoVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botonReservar()


    }


    private fun addVehiculo(vehiculo: VehiculoRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val llamada = getRetrofit().create(APIService::class.java).addVehiculo(vehiculo)

            } catch (e: EOFException) {
                Log.e("ERROR", e.toString())
            }
        }
    }


    private fun botonReservar() {

        val cliente = intent.getSerializableExtra("cliente") as ClienteResponse

        binding.btnGuardar.setOnClickListener {

            if (binding.etMarca.text.toString().isEmpty() ||
                binding.etModelo.text.toString().isEmpty() ||
                binding.etMatricula.text.toString().isEmpty() ||
                binding.etColor.text.toString().isEmpty() ||
                binding.etAO.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val vehiculo = VehiculoRequest(binding.etMarca.text.toString(),
                        binding.etModelo.text.toString(),
                        binding.etMatricula.text.toString(),
                        binding.etColor.text.toString(),
                        binding.etAO.text.toString(),
                        cliente = cliente)
                addVehiculo(vehiculo)

                Toast.makeText(this, "Vehiculo agregado", Toast.LENGTH_SHORT).show()
                finish()
            }


        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}