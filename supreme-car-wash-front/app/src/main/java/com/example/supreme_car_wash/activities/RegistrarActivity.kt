package com.example.supreme_car_wash.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityRegistrarBinding

class RegistrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}