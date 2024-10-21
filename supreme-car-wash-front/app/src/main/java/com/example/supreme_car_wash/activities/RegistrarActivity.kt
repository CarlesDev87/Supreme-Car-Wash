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
import com.example.supreme_car_wash.databinding.ActivityRegistrarBinding
import com.example.supreme_car_wash.requests.ClienteRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException

class RegistrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botonRegistrar()

    }

    private fun botonRegistrar() {
        binding.btnGuardar.setOnClickListener {
            if (binding.etNombre.text.toString().isEmpty() ||
                binding.etApellidos.text.toString().isEmpty() ||
                binding.etEmail.text.toString().isEmpty() ||
                binding.etDireccion.text.toString().isEmpty() ||
                binding.etTelefono.text.toString().isEmpty() ||
                binding.etPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val cliente =  ClienteRequest(
                    binding.etNombre.text.toString(),
                    binding.etApellidos.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etDireccion.text.toString(),
                    binding.etTelefono.text.toString(),
                    binding.etPassword.text.toString())
                addCliente(cliente)

                Toast.makeText(this, "Cliente registrado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun addCliente(cliente: ClienteRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val llamada = getRetrofit().create(APIService::class.java).addCliente(cliente)
            } catch (e: EOFException) {
             Log.e("ERROR", e.toString())
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