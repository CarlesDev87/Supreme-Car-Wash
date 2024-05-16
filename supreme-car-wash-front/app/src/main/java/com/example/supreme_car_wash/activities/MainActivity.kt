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
    private lateinit var fragmentMain: MainFragment
    private lateinit var fragmentPerfil: PerfilFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentMain = MainFragment()
        fragmentPerfil = PerfilFragment()


        supportFragmentManager.beginTransaction().replace(R.id.frgMain, fragmentMain).commit()


        //ESTE CODIGO ES EL QUE VOY A MIGRAR DE MAIN ACTTIVITY A FRAGMENT SERVICIOS PARA QUE LA TOOLBAR SOLO APAREZCA ALLI

        val cliente = intent.getSerializableExtra("cliente") as? ClienteResponse

        //Hago una instancia de cliente y la envio cuando invoco al fragment de servicios
        val frgServicios: ServiciosFragment = ServiciosFragment.newInstance(cliente as ClienteResponse)

        /*

        if (cliente != null) {
            binding.nombreUsuario.text = "${cliente.nombre} ${cliente.apellido}"

        }


        /*
        * ESTE CODIGO HACE QUE LA TOOLBAR SE PINTE DE ROJO CUANDO SE PLIEGA LA COLLAPSING TOOLBAR Y
        * MUESTRE EL NOMBRE DEL CLIENTE COMO TITULO DE LA TOOLBAR
        * */

        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange
            if (abs(verticalOffset) == totalScrollRange) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.principal))

                //Seccion donde ponemos el nombre del cliente a la toolbar cuando se pliega la collapsing toolbar

               /* if (cliente != null) {
                    toolbar.setTitle("${cliente.nombre} ${cliente.apellido}")
                } */

            } else  {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.principal))

            }
        })
        */
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

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


}


