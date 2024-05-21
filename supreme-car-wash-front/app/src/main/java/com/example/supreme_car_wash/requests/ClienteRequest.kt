package com.example.supreme_car_wash.requests

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ClienteRequest(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
) : Serializable
