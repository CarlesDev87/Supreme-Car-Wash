package com.example.supreme_car_wash.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.ActivityMainBinding
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        }
    }
