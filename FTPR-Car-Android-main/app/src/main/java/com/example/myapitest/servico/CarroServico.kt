package com.example.myapitest.servico

import com.example.myapitest.model.Carro
import com.example.myapitest.model.CarroConsulta
import com.example.myapitest.model.RespostaBase
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.ArrayList

interface CarroServico {

    @GET("car")
    fun listarCarros(): Call<ArrayList<Carro>>

    @GET("car/{idCarro}")
    fun buscarCarroPeloId(@Path("idCarro") idCarro: String): Call<CarroConsulta>

    @DELETE("car/{idCarroDeletar}")
    fun deletarCarro(@Path("idCarroDeletar") idCarroDeletar: String): Call<RespostaBase>

    @POST("car")
    fun cadastrarCarro(@Body carroCadastrar: Carro): Call<Carro>

    @PATCH("car/{idCarroEditar}")
    fun editarCarro(@Path("idCarroEditar") idCarroEditar: String, @Body carroEditar: Carro): Call<Carro>

}