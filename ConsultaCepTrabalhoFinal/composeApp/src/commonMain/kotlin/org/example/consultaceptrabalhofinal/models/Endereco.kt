package org.example.consultaceptrabalhofinal.models

import kotlinx.serialization.Serializable

@Serializable
data class Endereco(
    val cep: String = "",
    val logradouro: String = "",
    val localidade: String = "",
    val bairro: String = "",
    val uf: String = ""
)