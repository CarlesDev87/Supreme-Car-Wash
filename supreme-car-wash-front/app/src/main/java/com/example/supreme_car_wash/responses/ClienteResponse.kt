package com.example.supreme_car_wash.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ClienteResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val pass: String,
    @SerializedName("vehiculo") val vehiculo: String,
): Serializable {
}