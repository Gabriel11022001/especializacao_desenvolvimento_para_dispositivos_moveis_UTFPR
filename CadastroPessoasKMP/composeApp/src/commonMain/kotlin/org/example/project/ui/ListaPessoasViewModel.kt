package org.example.project.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.data.PessoaRepositorio

class ListaPessoasViewModel: ViewModel() {

    // repositório
    private val pessoaRepositorio: PessoaRepositorio = PessoaRepositorio()
    // controlar o state da aplicação
    var state: TelaListaPessoasState by mutableStateOf(TelaListaPessoasState())
        private set // vai ser somente leitura, ou seja, só pode ser modificada no escopo da classe ListaPessoasViewModel

    init {
        carregarPessoas()
    }

    // consultar pessoas no banco de dados
    fun carregarPessoas() {

        if (state.carregando) {

            return
        }

        state = state.copy(
            carregando = true,
            ocorreuErro = false,
            pessoas = listOf()
        )

        viewModelScope.launch {

            try {
                val pessoas = pessoaRepositorio.listar()

                if (pessoas.isEmpty()) {
                    state = state.copy(carregando = false, ocorreuErro = false, pessoas = listOf())
                } else {
                    state = state.copy(carregando = false, ocorreuErro = false, pessoas = pessoas)
                }

            } catch (e: Exception) {
                println("Erro ao tentar-se consultar as pessoas: ${ e.message }")

                state = state.copy(carregando = false, ocorreuErro = true, pessoas = listOf())
            }

        }

    }

}