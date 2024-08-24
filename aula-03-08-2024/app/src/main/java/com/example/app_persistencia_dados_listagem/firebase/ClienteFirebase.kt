package com.example.app_persistencia_dados_listagem.firebase

import android.util.Log
import com.example.app_persistencia_dados_listagem.model.Cliente
import java.util.ArrayList

class ClienteFirebase: FirebaseBase() {

    fun cadastrar(cliente: Cliente) {
        Log.i("teste_firebase", "Caiu no método para cadastrar cliente no firebase...")
        val dadosCliente = hashMapOf(
            "id" to cliente.id,
            "nome" to cliente.nome,
            "email" to cliente.email
        )

        super.firebase.collection("clientes")
            .document(cliente.id.toString())
            .set(dadosCliente)
            .addOnSuccessListener {
                Log.d("teste_cadastro_firebase", "Cliente cadastrado com sucesso no firebase!")
            }
            .addOnFailureListener {
                Log.e("teste_cadastro_firebase", "Erro cadastro firebase: ${ it.message.toString() }")
            }
    }

}