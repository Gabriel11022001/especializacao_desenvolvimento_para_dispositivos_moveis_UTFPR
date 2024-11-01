package com.example.myapitest.servico

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Servico {

    private val urlBase: String = "http://10.0.2.2:3000/"

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(this.urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCarroServico(): CarroServico {

        return this.getRetrofit().create(CarroServico::class.java)
    }

}