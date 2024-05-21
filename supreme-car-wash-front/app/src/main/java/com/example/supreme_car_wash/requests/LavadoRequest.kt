package com.example.supreme_car_wash.requests

import com.example.supreme_car_wash.responses.VehiculoResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LavadoRequest(
    @SerializedName("fechaLavado") val fechaCreacion: String,
    @SerializedName("tipoLavado") val tipoLavado: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("precio") val precio: Int,
    @SerializedName("estado") val estado: String,
    @SerializedName("vehiculo") val vehiculo: VehiculoResponse
) : Serializable