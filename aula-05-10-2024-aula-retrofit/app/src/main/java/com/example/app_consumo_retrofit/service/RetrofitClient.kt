package com.example.app_consumo_retrofit.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val BASE_URL: String = "http://192.168.0.104:3000/"
    val apiService: ApiService

    init {
        this.apiService = this.getRetrofit().create(ApiService::class.java)
    }

    // configurar o retrofit
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(this.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}