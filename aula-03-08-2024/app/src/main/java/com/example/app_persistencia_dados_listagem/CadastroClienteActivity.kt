package com.example.app_persistencia_dados_listagem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_persistencia_dados_listagem.dao.ClienteDAO
import com.example.app_persistencia_dados_listagem.model.Cliente

class CadastroClienteActivity : AppCompatActivity(), OnClickListener {

    private lateinit var edtNome: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnInserir: Button
    private lateinit var clienteDAO: ClienteDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cliente)

        // mapear elementos de interface
        this.edtNome = findViewById(R.id.edt_nome)
        this.edtEmail = findViewById(R.id.edt_email)
        this.btnInserir = findViewById(R.id.btn_inserir)

        // mapear evento
        this.btnInserir.setOnClickListener(this)

        this.clienteDAO = ClienteDAO(this)
    }

    private fun validarFormulario(): Boolean {
        var ok: Boolean = true
        val nome: String = this.edtNome.text.toString()
        val email: String = this.edtEmail.text.toString()

        if (nome.trim() == "") {
            ok = false
            this.edtNome.error = "Informe o nome do cliente!"
        } else if (nome.trim().length < 3) {
            ok = false
            this.edtNome.error = "O nome deve possuir no mínimo 3 caracteres!"
        }

        if (email.trim() == "") {
            ok = false
            this.edtEmail.error = "Informe o e-mail do cliente!"
        }

        return ok
    }

    private fun cadastrarCliente() {

        try {

            if (this.validarFormulario()) {
                val nome: String = this.edtNome.text.toString().trim()
                val email: String = this.edtEmail.text.toString().trim()
                val clienteCadastrar: Cliente = Cliente(
                    0,
                    nome,
                    email
                )

                val idClienteCadastrado: Int = this.clienteDAO.cadastrar(clienteCadastrar)

                if (idClienteCadastrado > 0) {
                    Toast.makeText(applicationContext, "Cliente cadastrado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                    Log.d("id_cliente_cadastrado", idClienteCadastrado.toString())

                    val clientesCadastrados: ArrayList<Cliente> = this.clienteDAO.listarTodos()

                    if (clientesCadastrados.size > 0) {

                        for (cliente in clientesCadastrados) {
                            Log.d("cliente", "id: ${ cliente.id } | nome: ${ cliente.nome } | e-mail: ${ cliente.email }")
                        }

                    }

                } else {
                    Toast.makeText(applicationContext, "Ocorreu um erro ao tentar-se cadastrar o cliente!", Toast.LENGTH_LONG)
                        .show()
                }

            }

        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ocorreu um erro ao tentar-se cadastrar o cliente!", Toast.LENGTH_LONG)
                .show()
            Log.e("erro_cadastrar_cliente", e.message.toString())
        }

    }

    override fun onClick(p0: View?) {
        this.cadastrarCliente()
    }

}