package org.example.project.ui

import org.example.project.Pessoa

data class TelaListaPessoasState(
    val carregando: Boolean = false,
    val ocorreuErro: Boolean = false,
    val pessoas: List<Pessoa> = listOf()
)
