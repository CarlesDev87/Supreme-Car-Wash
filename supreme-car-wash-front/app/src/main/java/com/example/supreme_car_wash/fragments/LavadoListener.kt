package com.example.supreme_car_wash.fragments

import com.example.supreme_car_wash.responses.LavadoResponse

interface LavadoListener {

    fun onLavadoSeleccionado(lavado: LavadoResponse)

}