package com.example.app_contas_a_pagar.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.app_contas_a_pagar.model.Lancamento
import java.util.ArrayList

class LancamentoDAO(contexto: Context): BaseDAO(contexto) {

    fun salvar(lancamento: Lancamento) {
        val contentValuesCadastroLancamento: ContentValues = ContentValues()
        contentValuesCadastroLancamento.put("valor", lancamento.valor)
        contentValuesCadastroLancamento.put("data_lancamento", lancamento.dataLancamento)
        contentValuesCadastroLancamento.put("paga", lancamento.pago)
        contentValuesCadastroLancamento.put("detalhe", lancamento.detalhe)
        contentValuesCadastroLancamento.put("tipo", lancamento.tipo)
        super.bancoDados.insert("tb_contas_pagar", null, contentValuesCadastroLancamento)
    }

    fun buscarTodos(): ArrayList<Lancamento> {
        val lancamentos: ArrayList<Lancamento> = ArrayList()
        val cursor: Cursor = super.bancoDados.rawQuery("SELECT * FROM tb_contas_pagar ORDER BY id ASC", emptyArray())

        if (cursor != null) {

            while (cursor.moveToNext()) {
                val lancamento: Lancamento = Lancamento()
                lancamento.id = cursor.getInt(cursor.getColumnIndex("id"))
                lancamento.valor = cursor.getDouble(cursor.getColumnIndex("valor"))
                lancamento.dataLancamento = cursor.getString(cursor.getColumnIndex("data_lancamento"))
                lancamento.pago = cursor.getInt(cursor.getColumnIndex("paga")) == 1
                lancamento.tipo = cursor.getString(cursor.getColumnIndex("tipo"))
                lancamento.detalhe = cursor.getString(cursor.getColumnIndex("detalhe"))

                lancamentos.add(lancamento)
            }

            cursor.close()
        }

        return lancamentos
    }

    fun deletar(idLancamento: Int) {
        super.bancoDados.delete("tb_contas_pagar", "id = ?", arrayOf(idLancamento.toString()))
    }

    fun alterarStatusPago(idLancamento: Int) {
        val contentValues: ContentValues = ContentValues()
        contentValues.put("paga", true)
        super.bancoDados.update("tb_contas_pagar", contentValues, "id = ?", arrayOf(idLancamento.toString()))
    }

}