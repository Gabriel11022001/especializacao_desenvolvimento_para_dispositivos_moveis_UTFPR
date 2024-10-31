package com.example.app_consumo_retrofit.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val BASE_URL: String = "http://192.168.1.5:3000/"
    val apiService: ApiService

    private val loggerInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggerInterceptor)
        .addInterceptor(GeoLocationInterceptor())
        .build()

    init {
        this.apiService = this.getRetrofit().create(ApiService::class.java)
    }

    // configurar o retrofit
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(this.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(this.okHttpClient)
            .build()
    }

}