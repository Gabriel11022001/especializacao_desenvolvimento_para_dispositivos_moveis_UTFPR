package com.example.constraint_layout.repositorio

import com.example.constraint_layout.Cliente
import kotlin.jvm.Throws

class ClienteRepositorio {

    private val clientes: ArrayList<Cliente> = ArrayList()

    constructor() {
        this.cadastrarClientesBase()
    }

    private fun cadastrarClientesBase() {

        for (indice in 0..<100) {
            var id: Int = 0

            if (this.clientes.size == 0) {
                id = 1
            } else {
                val ultimoCliente = this.clientes.last()
                id = ultimoCliente.id + 1
            }

            val clienteCadastrar: Cliente = Cliente(
                id,
                "Cliente " + id,
                "emailcliente" + id + "@gmail.com",
                "(14) 99877-987" + id
            )
            this.clientes.add(clienteCadastrar)
        }

    }

    fun obterClientes(): ArrayList<Cliente> {

        return this.clientes
    }

    private fun validarExisteClienteComNomeInformadoCadastro(nome: String): Boolean {

        for (cliente in this.clientes) {

            if (cliente.nome.trim().equals(nome.trim())) {

                return true
            }

        }

        return false
    }

    fun cadastrar(cliente: Cliente) {

        if (this.validarExisteClienteComNomeInformadoCadastro(cliente.nome)) {
            throw Exception("Já existe um cliente cadastrado com esse mesmo nome no banco de dados!")
        }

        this.clientes.add(cliente)
    }

}