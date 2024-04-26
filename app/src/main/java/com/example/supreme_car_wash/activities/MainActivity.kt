package com.example.supreme_car_wash.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityMainBinding
import com.example.supreme_car_wash.fragments.CitasFragment
import com.example.supreme_car_wash.fragments.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentMain: MainFragment
    private lateinit var fragmentCitas: CitasFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentMain = MainFragment()
        fragmentCitas = CitasFragment()

        supportFragmentManager.beginTransaction().add(R.id.frgMain, fragmentMain).commit()




        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.bvn_inicio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frgMain, fragmentMain).commit()
                }
                R.id.bvn_citas -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frgMain, fragmentCitas).commit()
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


