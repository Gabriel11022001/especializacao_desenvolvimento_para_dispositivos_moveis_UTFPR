package org.example.project

class Pessoa(
    val id: Int = 0,
    val nomeCompleto: String = "",
    val telefone: String = "",
    val email: String = "",
    val cpf: String = "",
    val endereco: Endereco = Endereco()
) {

}