package com.example.supreme_car_wash.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityLoginBinding
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}