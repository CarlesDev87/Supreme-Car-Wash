package com.example.supreme_car_wash.activities


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityMainBinding
import com.example.supreme_car_wash.fragments.MainFragment
import com.example.supreme_car_wash.fragments.PerfilFragment
import com.example.supreme_car_wash.fragments.ServiciosFragment
import com.example.supreme_car_wash.responses.ClienteResponse
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("cliente") as? ClienteResponse

        //Hago una instancia de cliente y la envio a los tres fragments para que reciban el cliente del Login
        val frgServicios: ServiciosFragment = ServiciosFragment.newInstance(cliente as ClienteResponse)
        val fragmentMain: MainFragment = MainFragment.newInstance(cliente)
        val fragmentPerfil: PerfilFragment = PerfilFragment.newInstance(cliente)

        supportFragmentManager.beginTransaction().replace(R.id.frgMain, fragmentMain).commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {

                item ->

            when (item.itemId) {

                R.id.bvn_inicio -> {
                    supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(
                            R.anim.fade_in_fragment,
                            R.anim.fade_out_fragment,
                            R.anim.fade_in_fragment,
                            R.anim.fade_out_fragment
                        )
                        replace(R.id.frgMain, fragmentMain)
                        addToBackStack(null)
                        commit()
                    }
                }

                R.id.bvn_servicio -> {
                    supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(
                            R.anim.fade_in_fragment,
                            R.anim.fade_out_fragment,
                            R.anim.fade_in_fragment,
                            R.anim.fade_out_fragment
                        )
                        replace(R.id.frgMain, frgServicios)
                        addToBackStack(null)
                        commit()
                    }
                }

                R.id.bvn_perfil -> {
                    supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(
                            R.anim.fade_in_fragment,
                            R.anim.fade_out_fragment,
                            R.anim.fade_in_fragment,
                            R.anim.fade_out_fragment
                        )
                        replace(R.id.frgMain, fragmentPerfil)
                        addToBackStack(null)
                        commit()
                    }
                }

            }
            true
        }

    }

}


