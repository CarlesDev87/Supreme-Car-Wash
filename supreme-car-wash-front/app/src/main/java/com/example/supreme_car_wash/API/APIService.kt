package com.example.supreme_car_wash.API

import com.example.supreme_car_wash.responses.ClienteResponse
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.TokenResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {

@GET
suspend fun getLavados(@Url url: String): Response<List<LavadoResponse>>

@GET
suspend fun getClientes(@Url url:String): Response<List<ClienteResponse>>

@GET
suspend fun getVehiculos(@Url url: String): Response<List<VehiculoResponse>>

@GET
suspend fun loginCliente(@Url url: String): Response<TokenResponse>


}