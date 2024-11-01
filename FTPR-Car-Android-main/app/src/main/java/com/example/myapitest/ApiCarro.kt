package com.example.myapitest

import android.util.Log
import com.example.myapitest.model.Carro
import com.example.myapitest.model.CarroConsulta
import com.example.myapitest.model.RespostaBase
import com.example.myapitest.servico.CarroServico
import com.example.myapitest.servico.Servico
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCarro {

    fun listarCarros(
        onProcessandoRequisicao: () -> Unit,
        onErroRequisicao: (mensagemErro: String) -> Unit,
        onSucessoRequisicao: (ArrayList<Carro>) -> Unit
    ) {
        val servico: Servico = Servico()
        val carroServico: CarroServico = servico.getCarroServico()

        // processando a requisição
        onProcessandoRequisicao()

        carroServico.listarCarros()
            .enqueue(object : Callback<java.util.ArrayList<Carro>> {

                override fun onResponse(call: Call<java.util.ArrayList<Carro>>, response: Response<java.util.ArrayList<Carro>>) {
                    // processou a requisição com sucesso

                    if (response.isSuccessful) {
                        val carros: java.util.ArrayList<Carro> = response.body()!!

                        if (carros != null) {

                            carros.forEach { carro ->
                                Log.d("carro", carro.nomeCarro)
                            }

                        }

                        // processou com sucesso a requisição
                        onSucessoRequisicao(carros)
                    } else {
                        onErroRequisicao("Erro ao tentar-se consultar os carros.")
                    }

                }

                override fun onFailure(call: Call<java.util.ArrayList<Carro>>, t: Throwable) {
                    // ocorreu um erro ao tentar-se processar a requisição
                    onErroRequisicao("Erro ao tentar-se consultar os carros: ${ t.message }")
                }

            })

    }

    fun buscarCarroPeloId(
        idCarro: String,
        onProcessandoRequisicao: () -> Unit,
        onErroRequisicao: (mensagemErro: String) -> Unit,
        onSucessoRequisicao: (Carro) -> Unit
    ) {
        val servico: Servico = Servico()
        val carroServico: CarroServico = servico.getCarroServico()

        // processando a requisição
        onProcessandoRequisicao()

        carroServico.buscarCarroPeloId(idCarro)
            .enqueue(object : Callback<CarroConsulta> {

                override fun onResponse(call: Call<CarroConsulta>, response: Response<CarroConsulta>) {
                    // processou a requisição com sucesso

                    if (response.isSuccessful) {
                        val carro = response.body()!!

                        if (carro != null) {
                            val carroApresentar = carro!!.carro

                            onSucessoRequisicao(carroApresentar!!)
                        } else {
                            onErroRequisicao("Carro não encontrado.")
                        }

                    } else {
                        onErroRequisicao("Erro ao tentar-se buscar o carro pelo id.")
                    }

                }

                override fun onFailure(call: Call<CarroConsulta>, t: Throwable) {
                    // ocorreu um erro ao tentar-se buscar o carro pelo id no servidor
                    onErroRequisicao("Erro: ${ t.message }")
                }

            })
    }

    fun deletarCarro(
        idCarro: String,
        onProcessandoRequisicao: () -> Unit,
        onErroRequisicao: (mensagemErro: String) -> Unit,
        onSucessoRequisicao: (mensagemSucesso: String) -> Unit
    ) {
        val servico: Servico = Servico()
        val carroServico: CarroServico = servico.getCarroServico()

        onProcessandoRequisicao()

        carroServico.deletarCarro(idCarro)
            .enqueue(object : Callback<RespostaBase> {

                override fun onResponse(call: Call<RespostaBase>, response: Response<RespostaBase>) {

                    if (response.isSuccessful) {
                        onSucessoRequisicao("Carro deletado com sucesso.")
                    } else {
                        Log.e("erro_deletar_carro", "Erro deletar carro!")
                        onErroRequisicao("Erro ao tentar-se deletar o carro.")
                    }

                }

                override fun onFailure(call: Call<RespostaBase>, t: Throwable) {
                    onErroRequisicao("Erro ao tentar-se deletar o carro: ${ t.message.toString() }")
                }

            })

    }

}