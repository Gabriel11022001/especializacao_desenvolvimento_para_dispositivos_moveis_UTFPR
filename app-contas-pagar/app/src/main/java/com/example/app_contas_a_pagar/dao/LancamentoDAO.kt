package com.example.app_contas_a_pagar.dao

import android.content.Context
import com.example.app_contas_a_pagar.model.Lancamento
import java.util.ArrayList

class LancamentoDAO(contexto: Context): BaseDAO(contexto) {

    fun salvar(lancamento: Lancamento) {

    }

    fun buscarTodos(): ArrayList<Lancamento> {
        val landamentos: ArrayList<Lancamento> = ArrayList()

        return landamentos
    }

    fun deletar(idLancamento: Int) {

    }

    fun alterarStatusPago(idLancamento: Int) {

    }

}