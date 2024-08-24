package com.example.app_contas_a_pagar.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase

open class BaseDAO(val contexto: Context) {

    private val gerenciadorBancoDados: GerenciadorBancoDados
    protected val bancoDados: SQLiteDatabase

    init {
        this.gerenciadorBancoDados = GerenciadorBancoDados(contexto)
        this.bancoDados = this.gerenciadorBancoDados.writableDatabase!!
    }

}