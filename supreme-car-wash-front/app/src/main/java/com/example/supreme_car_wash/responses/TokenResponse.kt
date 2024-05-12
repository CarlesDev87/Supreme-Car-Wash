package com.example.supreme_car_wash.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TokenResponse(
   @SerializedName("token") val token: String
): Serializable {
}