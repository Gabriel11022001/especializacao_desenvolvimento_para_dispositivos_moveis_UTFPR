package com.example.app_consumo_retrofit.service

import com.example.app_consumo_retrofit.Item
import com.example.app_consumo_retrofit.Value
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("items")
    suspend fun listarTodos(): List<Item>

    @GET("items/{id}")
    suspend fun buscarPeloId(@Path("id") id: String): Item

    @DELETE("items/{id}")
    suspend fun deletar(@Path("id") id: String)

    @POST("items")
    suspend fun cadastrar(@Body value: Value): Item

    @PATCH("items/{id}")
    suspend fun editar(@Path("id") id: String, @Body value: Value): Item

}