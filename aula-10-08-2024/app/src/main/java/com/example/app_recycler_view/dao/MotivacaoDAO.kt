package com.example.app_recycler_view.dao

import android.content.Context
import com.example.app_recycler_view.R
import com.example.app_recycler_view.model.Motivacao

class MotivacaoDAO(val contexto: Context) {

    private var motivacoes: ArrayList<Motivacao> = ArrayList()

    init {
        this.definirMotivaoes()
    }

    private fun definirMotivaoes() {
        val primeiraMotivacao: Motivacao = Motivacao()
        val segundaMotivacao: Motivacao = Motivacao()
        val terceiraMotivacao: Motivacao = Motivacao()
        val quartaMotivacao: Motivacao = Motivacao()
        val quintaMotivacao: Motivacao = Motivacao()

        // mapeando estados
        primeiraMotivacao.id = 1
        segundaMotivacao.id = 2
        terceiraMotivacao.id = 3
        quartaMotivacao.id = 4
        quintaMotivacao.id = 5

        primeiraMotivacao.descricao = this.contexto.getString(R.string.primeira_motivacao)
        segundaMotivacao.descricao = this.contexto.getString(R.string.segunda_motivacao)
        terceiraMotivacao.descricao = this.contexto.getString(R.string.terceira_motivacao)
        quartaMotivacao.descricao = this.contexto.getString(R.string.quarta_motivacao)
        quintaMotivacao.descricao = this.contexto.getString(R.string.quinta_motivacao)

        this.motivacoes.add(primeiraMotivacao)
        this.motivacoes.add(segundaMotivacao)
        this.motivacoes.add(terceiraMotivacao)
        this.motivacoes.add(quartaMotivacao)
        this.motivacoes.add(quintaMotivacao)
    }

    fun buscarTodasMotivacoes(): ArrayList<Motivacao> {

        return this.motivacoes
    }

    fun buscarMotivacaoPeloId(id: Int): Motivacao? {
        var motivacao: Motivacao? = null

        for (motivacaoConsulta in this.motivacoes) {

            if (motivacaoConsulta.id == id) {
                motivacao = motivacaoConsulta
            }

        }

        if (motivacao != null) {

            return motivacao
        }

        return null
    }

}