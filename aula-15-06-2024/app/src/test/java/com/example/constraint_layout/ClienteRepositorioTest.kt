package com.example.constraint_layout

import com.example.constraint_layout.repositorio.ClienteRepositorio
import org.junit.Test
import org.junit.Assert.*

class ClienteRepositorioTest {

    @Test
    fun validarSeCemClientesForamCadastradosCorretamente() {
        println("Testando se a foram cadastrados 100 clientes!")
        val clienteRepositorio: ClienteRepositorio = ClienteRepositorio()
        val quantidadeClientesCadastrados: Int = clienteRepositorio.obterClientes().size
        assertEquals(100, quantidadeClientesCadastrados)
    }

    @Test
    fun testeCadastrarClienteComNomeRepetido() {
        println("Testando se está sendo landaço execeção quando tento cadastrar clientes com nome repetido!")
        val clienteRepositorio = ClienteRepositorio()
        val primeiroCliente: Cliente = Cliente(
            200,
            "Gabriel",
            "(14) 99877-9876",
            "fulano@teste.com"
        )
        val segundoCliente: Cliente = Cliente(
            201,
            "Gabriel",
            "(14) 99877-9875",
            "fulano2@teste.com"
        )
        clienteRepositorio.cadastrar(primeiroCliente)

        var lancouExcecao: Boolean = false

        try {
            clienteRepositorio.cadastrar(segundoCliente)
        } catch (e : Exception) {
            lancouExcecao = true
        }

        assertTrue(lancouExcecao)
    }

}