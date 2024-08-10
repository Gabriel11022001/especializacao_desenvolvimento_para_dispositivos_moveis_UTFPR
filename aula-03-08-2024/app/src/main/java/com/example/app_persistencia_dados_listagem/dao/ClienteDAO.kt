package com.example.app_persistencia_dados_listagem.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.app_persistencia_dados_listagem.ClienteNaoEncontradoException
import com.example.app_persistencia_dados_listagem.model.Cliente

class ClienteDAO(contexto: Context): BaseDAO(contexto) {

    fun cadastrar(cliente: Cliente): Int {
        val contentValues: ContentValues = ContentValues()
        contentValues.put("nome", cliente.nome)
        contentValues.put("email", cliente.email)

        // o método insert da classe SQLiteDatabase insere dados na tabela informada
        return super.bancoDados.insert("tb_clientes", null, contentValues).toInt()
    }

    fun editar(cliente: Cliente): Int {
        val contentValues: ContentValues = ContentValues()
        contentValues.put("id", cliente.id)
        contentValues.put("nome", cliente.nome)
        contentValues.put("email", cliente.email)

        // o método update da classe SQLiteDatabase atualiza os dados na tabela informada
        return super.bancoDados.update("tb_clientes", contentValues, "id = ?", arrayOf( cliente.id.toString() ))
    }

    fun listarTodos(): ArrayList<Cliente> {
        val clientes: ArrayList<Cliente> = ArrayList()
        val cursor: Cursor = super.bancoDados.rawQuery("SELECT * FROM tb_clientes", null)

        if (cursor != null) {

            while (cursor.moveToNext()) {
                val cliente: Cliente = Cliente(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("email"))
                )
                clientes.add(cliente)
            }

            cursor.close()
        }

        return clientes
    }

    fun buscarPeloId(id: Int): Cliente {
        var cliente: Cliente? = null
        val queryConsultarCliente: String = "SELECT * FROM tb_clientes WHERE id = ?"
        val cursorConsultarCliente: Cursor = super.bancoDados.rawQuery(queryConsultarCliente, arrayOf( id.toString() ))

        if (cursorConsultarCliente != null) {

            if (cursorConsultarCliente.moveToFirst()) {
                cliente = Cliente(
                    cursorConsultarCliente.getInt(cursorConsultarCliente.getColumnIndex("id")),
                    cursorConsultarCliente.getString(cursorConsultarCliente.getColumnIndex("nome")),
                    cursorConsultarCliente.getString(cursorConsultarCliente.getColumnIndex("email"))
                )

                return cliente
            }

            cursorConsultarCliente.close()
        }

        throw ClienteNaoEncontradoException()
    }

    fun deletar(id: Int) {
        super.bancoDados.delete("tb_clientes", "id = ?", arrayOf( id.toString() ))
    }

}