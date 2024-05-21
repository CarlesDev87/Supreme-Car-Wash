package com.example.supreme_car_wash.responses

import com.google.gson.annotations.SerializedName

class VehiculoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("marca") val marca: String,
    @SerializedName("modelo") val modelo: String,
    @SerializedName("matricula") val matricula: String,
    @SerializedName("color") val color: String,
    @SerializedName("anyo") val anyo: String,
) {
}