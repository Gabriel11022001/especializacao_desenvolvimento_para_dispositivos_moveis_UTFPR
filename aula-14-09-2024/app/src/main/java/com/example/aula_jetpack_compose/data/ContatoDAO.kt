package com.example.aula_jetpack_compose.data

import java.util.ArrayList

class ContatoDAO {

    private constructor()

    companion object {

        var contatoDAO: ContatoDAO? = null

    }

    private val contatos: ArrayList<Contato> = ArrayList()

    fun salvar(contato: Contato): Contato {

        if (contato.id > 0) {
            // atualizar contato

            this.contatos.forEach {

                if (it.id == contato.id) {
                    it.id = contato.id
                    it.nome = contato.nome
                    it.email = contato.email
                    it.telefone = contato.telefone
                    it.sobrenome = contato.sobrenome
                    it.favorito = contato.favorito
                }

            }

        } else {
            // cadastrar novo contato
            this.contatos.add(contato)
        }

        return contato
    }

}