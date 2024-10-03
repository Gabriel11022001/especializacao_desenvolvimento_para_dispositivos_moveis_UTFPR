package com.example.aula_jetpack_compose.data

data class Contato(
    var id: Int = 0,
    var nome: String = "",
    var sobrenome: String = "",
    var telefone: String = "",
    var email: String = "",
    var favorito: Boolean = false
)