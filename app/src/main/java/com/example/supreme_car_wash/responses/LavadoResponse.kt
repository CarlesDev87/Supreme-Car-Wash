package com.example.supreme_car_wash.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate

data class LavadoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("estado") val estado: String,
    @SerializedName("fechaCreacion") val fechaCreacion: LocalDate,
    @SerializedName("precio") val precio: Int,
    @SerializedName("tipoLavado") val tipoLavado: String,
    @SerializedName("idVehiculo") val idVehiculo: Int
) : Serializable {
}