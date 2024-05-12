package com.example.supreme_car_wash.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityLoginBinding
import com.example.supreme_car_wash.responses.ClienteResponse
import com.google.android.gms.fido.fido2.api.common.Algorithm
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        * ESTE EVENTO GESTIONA LA APARICION Y LA DESAPARICION DE INPUTS CUANDO DAMOS CLICK AL BOTON INICIAR SESION */

        binding.btnLogin.setOnClickListener {
            val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

            binding.tieUsuario.startAnimation(fadeInAnimation)
            binding.tieUsuario.visibility = View.VISIBLE

            binding.tiePass.startAnimation(fadeInAnimation)
            binding.tiePass.visibility = View.VISIBLE

            binding.btnEntrar.startAnimation(fadeInAnimation)
            binding.btnEntrar.visibility = View.VISIBLE

            val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

            binding.btnLogin.startAnimation(fadeOutAnimation)
            binding.btnLogin.visibility = View.GONE

            binding.registrate.startAnimation(fadeOutAnimation)
            binding.registrate.visibility = View.GONE
        }

        binding.btnEntrar.setOnClickListener {

            val email: String = binding.etUsuario.text.toString().trim()
            val password: String = binding.etPass.text.toString().trim()

            val query = "/clientes/validar?email=$email&password=$password"

            loginCliente(query)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }

    private fun loginCliente(query: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = getRetrofit().create(APIService::class.java)
                    .loginCliente(query)

                Log.d("Respuesta", response.toString())

                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    val token = tokenResponse?.token



                    if (!token.isNullOrEmpty()) {
                        val sharedPreferences = getSharedPreferences("Validacion", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("token", token)
                        editor.apply()

                        runOnUiThread {
                            Snackbar.make(binding.root, "Login correcto", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        runOnUiThread {
                            Snackbar.make(binding.root, "Login incorrecto", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Snackbar.make(binding.root, "Response unsuccesful", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Log.e("Error de conexión", e.message, e)
                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Error de conexión: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
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
