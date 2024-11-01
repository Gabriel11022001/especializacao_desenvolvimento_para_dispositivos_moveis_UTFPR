package com.example.myapitest.servico

import com.example.myapitest.model.Carro
import com.example.myapitest.model.CarroConsulta
import com.example.myapitest.model.RespostaBase
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList

interface CarroServico {

    @GET("car")
    fun listarCarros(): Call<ArrayList<Carro>>

    @GET("car/{idCarro}")
    fun buscarCarroPeloId(@Path("idCarro") idCarro: String): Call<CarroConsulta>

    @DELETE("car/{idCarroDeletar}")
    fun deletarCarro(@Path("idCarroDeletar") idCarroDeletar: String): Call<RespostaBase>

}