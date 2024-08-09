package com.example.app_persistencia_dados_listagem.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class GerenciadorBancoDados(contexto: Context): SQLiteOpenHelper(
    contexto,
    Versoes.NOME_DB,
    null,
    Versoes.VERSAO_DB
) {

    override fun onCreate(p0: SQLiteDatabase?) {
        // método invocado para criar o banco de dados
        this.criarTabelaClientes(p0!!)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // método invocado quando queremos realizar uma atualização no banco
    }

    private fun criarTabelaClientes(bancoDados: SQLiteDatabase) {
        Log.d("teste_criar_tabela", "Criando a tabela de clientes!")
        // criar a tabela de clientes
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS tb_clientes(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL)")
        Log.d("teste_finalizou_criar_tabela", "Terminou de criar a tabela de clientes!")
    }

}