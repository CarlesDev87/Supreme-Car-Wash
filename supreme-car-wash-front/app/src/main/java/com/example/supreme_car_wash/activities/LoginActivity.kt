package com.example.supreme_car_wash.activities

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import com.auth0.jwt.interfaces.DecodedJWT
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.auth0.jwt.JWT
import com.example.supreme_car_wash.API.APIService
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityLoginBinding
import com.example.supreme_car_wash.fragments.MainFragment
import com.example.supreme_car_wash.responses.ClienteResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var cliente: ClienteResponse

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //METODO PRINCIPALES

        registrarCliente()
        animaciones()
        pulsarBotonEntrar()

    }

    private fun decodeToken(token: String): DecodedJWT {
        return try {
            JWT.decode(token)
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid token")
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

                    val decodedJWT: DecodedJWT? = token?.let { decodeToken(it) }

                    val id = decodedJWT?.getClaim("id")?.asInt()
                    val nombre = decodedJWT?.getClaim("nombre")?.asString()
                    val apellido = decodedJWT?.getClaim("apellido")?.asString()
                    val email = decodedJWT?.getClaim("email")?.asString()
                    val direccion = decodedJWT?.getClaim("direccion")?.asString()
                    val telefono = decodedJWT?.getClaim("telefono")?.asString()
                    val password = decodedJWT?.getClaim("password")?.asString()


                    cliente =
                        ClienteResponse(id, nombre, apellido, email, direccion, telefono, password)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("cliente", cliente)
                    startActivity(intent)

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

    private fun pulsarBotonEntrar() {
        binding.btnEntrar.setOnClickListener {

            val email: String = binding.etUsuario.text.toString().trim()
            val password: String = binding.etPass.text.toString().trim()

            val query = "/clientes/validar?email=$email&password=$password"

            loginCliente(query)

        }
    }


    private fun registrarCliente() {
        binding.registrate.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun animaciones() {
        //ESTE EVENTO GESTIONA LA APARICION Y LA DESAPARICION DE INPUTS CUANDO DAMOS CLICK AL BOTON INICIAR SESION */

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
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
