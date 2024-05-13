package com.example.supreme_car_wash.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityMainBinding
import com.example.supreme_car_wash.fragments.MainFragment
import com.example.supreme_car_wash.fragments.PerfilFragment
import com.example.supreme_car_wash.fragments.ServiciosFragment
import com.example.supreme_car_wash.responses.ClienteResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentMain: MainFragment
    private lateinit var fragmentPerfil: PerfilFragment
    private lateinit var fragmentServicios: ServiciosFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentMain = MainFragment()
        fragmentPerfil = PerfilFragment()
        fragmentServicios = ServiciosFragment()

        supportFragmentManager.beginTransaction().add(R.id.frgMain, fragmentMain).commit()

        val cliente = intent.getSerializableExtra("cliente") as? ClienteResponse

        if (cliente != null) {
            binding.nombreUsuario.text = "${cliente.nombre} ${cliente.apellido}"

        }

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
                        replace(R.id.frgMain, fragmentServicios)
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

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}


