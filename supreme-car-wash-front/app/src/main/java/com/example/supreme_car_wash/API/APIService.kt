package com.example.supreme_car_wash.API

import com.example.supreme_car_wash.requests.ClienteRequest
import com.example.supreme_car_wash.requests.LavadoRequest
import com.example.supreme_car_wash.requests.VehiculoRequest
import com.example.supreme_car_wash.responses.ClienteResponse
import com.example.supreme_car_wash.responses.LavadoResponse
import com.example.supreme_car_wash.responses.TokenResponse
import com.example.supreme_car_wash.responses.VehiculoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {

@GET
suspend fun getLavados(@Url url: String): Response<List<LavadoResponse>>

@GET
suspend fun getClientes(@Url url:String): Response<List<ClienteResponse>>

@GET
suspend fun getCliente (@Url url:String): Response<ClienteResponse>

@GET
suspend fun getVehiculos(@Url url: String): Response<List<VehiculoResponse>>

@GET("/vehiculos/id/{id}")
suspend fun getVehiculo(@Path("id") id: Int): Response<VehiculoResponse>

@GET
suspend fun loginCliente(@Url url: String): Response<TokenResponse>

@PUT
suspend fun updateCliente(@Body cliente: ClienteRequest): Response<ClienteRequest>

@POST("/lavados")
suspend fun addLavado(@Body lavado: LavadoRequest): LavadoRequest

@POST("/clientes")
suspend fun addCliente(@Body cliente: ClienteRequest): ClienteRequest

@POST("/vehiculos")
suspend fun addVehiculo(@Body vehiculo: VehiculoRequest): VehiculoRequest

}