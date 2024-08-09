package com.example.app_persistencia_dados_listagem.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase

open class BancoDados(contexto: Context) {

    private val gerenciadorBancoDados: GerenciadorBancoDados = GerenciadorBancoDados(contexto)
    protected var bancoDados: SQLiteDatabase = gerenciadorBancoDados.writableDatabase

}