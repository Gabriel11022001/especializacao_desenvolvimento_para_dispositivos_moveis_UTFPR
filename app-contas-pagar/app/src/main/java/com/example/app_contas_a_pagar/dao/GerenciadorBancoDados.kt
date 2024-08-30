package com.example.app_contas_a_pagar.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GerenciadorBancoDados(val contexto: Context): SQLiteOpenHelper(
    contexto,
    "db_contas_pagar",
    null,
    1
) {

    override fun onCreate(p0: SQLiteDatabase?) {
        // criar tabela de lançamentos
        p0!!.execSQL("CREATE TABLE IF NOT EXISTS tb_contas_pagar(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "valor DOUBLE NOT NULL," +
                "data_lancamento TEXT NOT NULL," +
                "paga INTEGER NOT NULL," +
                "tipo TEXT," +
                "detalhe TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}