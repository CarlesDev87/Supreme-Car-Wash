package com.example.supreme_car_wash.requests

import com.example.supreme_car_wash.responses.ClienteResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VehiculoRequest(
    @SerializedName("marca") val marca: String,
    @SerializedName("modelo") val modelo: String,
    @SerializedName("matricula") val matricula: String,
    @SerializedName("color") val color: String,
    @SerializedName("anyo") val anyo : String,
    @SerializedName("cliente") val cliente : ClienteResponse,
): Serializable
