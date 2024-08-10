package com.example.app_persistencia_dados_listagem

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_persistencia_dados_listagem.model.Cliente
import com.example.app_persistencia_dados_listagem.dao.ClienteDAO

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var edtCodigo: EditText
    private lateinit var edtNome: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnInserir: Button
    private lateinit var btnDeletar: Button
    private lateinit var btnEditar: Button
    private lateinit var btnListar: Button
    private lateinit var btnConsultar: Button
    private lateinit var clienteDAO: ClienteDAO

    /**
     * no onCreate(), geralmente nós realizamos os seguintes processos:
     * 1 - inflamos o layout pra ele ser manipulado por programação
     * 2 - mapear os elementos de interface
     * 3 - configurar os eventos
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mapear elementos de interface
        this.mapearElementosInterface()
        // mapear eventos
        this.mapearEventos()

        this.clienteDAO = ClienteDAO(this)
    }

    /**
     * no ciclo de vida, o método onStart() é invocado após o
     * onCreate()
     */
    override fun onStart() {
        super.onStart()
        Log.d("onStart", "Executou o método onStart() no ciclo de vida...")
    }

    /**
     * no ciclo de vida, o onResume() é invocado após o onStart(),
     * geralmente nesse método nós fazemos o seguinte:
     * 1 - consultamos dados no banco de dados ou buscamos dados por meio de uma Rest API
     * 2 - apresentamos os dados para o usuário
     */
    override fun onResume() {
        super.onResume()
        Log.d("onResume", "Executou o método onResume() no ciclo de vida...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "Executou o método onStop() no ciclo de vida...")
    }

    override fun onDestroy() {
        super.onDestroy()

        /*try {
            val builderAlertDialog = AlertDialog.Builder(applicationContext)
            // configurando o builder do AlertDialog
            builderAlertDialog.setTitle("Sair")
            builderAlertDialog.setMessage("Deseja sair da aplicação?")
            builderAlertDialog.setCancelable(false)
            builderAlertDialog.setPositiveButton("Sim") { dialog, which ->
                // fechar a activity
                Log.d("teste", "Finalizou a aplicação...")
                super.onDestroy()
            }

            val alertDialog = builderAlertDialog.create()
            alertDialog.show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ocorreu um erro: ${ e.message.toString() }", Toast.LENGTH_LONG)
                .show()
        }*/

    }

    private fun mapearElementosInterface() {
        this.edtCodigo = findViewById(R.id.edt_codigo)
        this.edtNome = findViewById(R.id.edt_nome)
        this.edtEmail = findViewById(R.id.edt_email)
        this.btnInserir = findViewById(R.id.btn_inserir)
        this.btnListar = findViewById(R.id.btn_listar)
        this.btnDeletar = findViewById(R.id.btn_deletar)
        this.btnConsultar = findViewById(R.id.btn_pesquisar)
        this.btnEditar = findViewById(R.id.btn_editar)
    }

    private fun mapearEventos() {
        this.btnInserir.setOnClickListener(this)
        this.btnEditar.setOnClickListener(this)
        this.btnDeletar.setOnClickListener(this)
        this.btnListar.setOnClickListener(this)
        this.btnConsultar.setOnClickListener(this)
    }

    private fun validarFormulario(edicao: Boolean): Boolean {
        var ok: Boolean = true
        var codigo: String = this.edtCodigo.text.toString()
        var nome: String = this.edtNome.text.toString()
        var email: String = this.edtEmail.text.toString()

        if (edicao) {

            if (codigo.trim() == "") {
                ok = false
                this.edtCodigo.error = "Informe o código do cliente!"
            } else if (codigo.trim().toInt() <= 0) {
                ok = false
                this.edtCodigo.error = "Código inválido!"
            }

        }

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

            if (this.validarFormulario(false)) {
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

    private fun editarCliente() {

        try {

            if (this.validarFormulario(true)) {
                var id: Int = this.edtCodigo.text.toString().toInt()
                var nome: String = this.edtNome.text.toString()
                var email: String = this.edtEmail.text.toString()
                val clienteEditar: Cliente = Cliente(id, nome, email)
                val idClienteEditado: Int = this.clienteDAO.editar(clienteEditar)

                if (idClienteEditado == 0) {
                    Toast.makeText(applicationContext, "Ocorreu um erro ao tentar-se editar o cliente!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    // o cliente foi alterado com sucesso
                    Toast.makeText(applicationContext, "Os dados do cliente foram alterados com sucesso!", Toast.LENGTH_LONG)
                        .show()
                }

            }

        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ocorreu um erro ao tentar-se editar o cliente!", Toast.LENGTH_LONG)
                .show()
            Log.e("erro_editar_cliente", e.message.toString())
        }

    }

    private fun listarClientes() {

        try {
            val clientes: ArrayList<Cliente> = this.clienteDAO.listarTodos()

            if (clientes.size > 0) {

                for (cliente in clientes) {
                    Log.d("nome_cliente", cliente.nome.uppercase())
                    Log.d("email_cliente", cliente.email.uppercase())
                }

            } else {
                Toast.makeText(applicationContext, "Não existem clientes cadastrados!", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()
        }

    }

    private fun buscarCliente() {

        /*try {
            val codigo: String = this.edtCodigo.text.toString()

            if (codigo.isEmpty()) {
                Toast.makeText(applicationContext, "Informe o código do cliente para consultar!", Toast.LENGTH_LONG)
                    .show()
            } else {
                val cliente: Cliente = this.clienteDAO.buscarPeloId(codigo.toInt())!!
                // encontrou o cliente
                this.edtCodigo.setText(cliente.id.toString())
                this.edtNome.setText(cliente.nome)
                this.edtEmail.setText(cliente.email)
            }

        } catch (e: ClienteNaoEncontradoException) {
            Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
                .show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ocorreu um erro ao tentar-se buscar os dados do cliente!", Toast.LENGTH_LONG)
                .show()
            Log.e("erro_consultar_cliente", e.message.toString())
        }*/

        // consultar com AlertDialog
        val builderAlertDialogConsultar = AlertDialog.Builder(this)
        builderAlertDialogConsultar.setTitle("Pesquisa")
        builderAlertDialogConsultar.setMessage("Digite o código do cliente")
        builderAlertDialogConsultar.setCancelable(false)

        val edtCodigoClienteConsultar: EditText = EditText(this)
        edtCodigoClienteConsultar.hint = "Digite o código..."
        builderAlertDialogConsultar.setView(edtCodigoClienteConsultar)

        // evento de consulta
        builderAlertDialogConsultar.setPositiveButton("Consultar") { dialogInterface, i ->

            try {
                Log.d("teste", "Clicou no botão positivo para consultar os clientes!")
                val codigo: String = edtCodigoClienteConsultar.text.toString()
                Log.d("codigo", codigo)

                if (codigo.isEmpty()) {
                    Toast.makeText(this, "Informe o código do cliente!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    val cliente: Cliente = clienteDAO.buscarPeloId(codigo.toInt())
                    edtCodigo.setText(cliente.id.toString())
                    edtNome.setText(cliente.nome)
                    edtEmail.setText(cliente.email)
                }

            } catch (e: Exception) {
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG)
                    .show()
                Log.e("erro", e.message.toString())
            }

        }

        // fechar o dialog
        builderAlertDialogConsultar.setNegativeButton("Fechar") { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        builderAlertDialogConsultar.show()
    }

    private fun deletarCliente() {

        try {
            val codigo: String = this.edtCodigo.text.toString().trim()

            if (codigo.isEmpty()) {
                Toast.makeText(applicationContext, "Informe o código do cliente para deletar!", Toast.LENGTH_LONG)
                    .show()
            } else {
                this.clienteDAO.buscarPeloId(id = codigo.toInt())
                this.clienteDAO.deletar(codigo.toInt())
                Toast.makeText(applicationContext, "Cliente deletado com sucesso!", Toast.LENGTH_LONG)
                    .show()
            }

        } catch (e: ClienteNaoEncontradoException) {
            Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ocorreu um erro ao tentar-se deletar o cliente!", Toast.LENGTH_LONG)
                .show()
            Log.e("erro_deletar_cliente", e.message.toString())
        }

    }

    override fun onClick(p0: View?) {
        val elementoInterface: View = p0!!

        if (elementoInterface.id == R.id.btn_inserir) {
            // cadastrar um cliente
            this.cadastrarCliente()
        } else if (elementoInterface.id == R.id.btn_editar) {
            // editar o cliente
            this.editarCliente()
        } else if (elementoInterface.id == R.id.btn_listar) {
            // listar todos os clientes
            // this.listarClientes()
            val intentRedirecionarTelaListagemClientes: Intent = Intent(applicationContext, ClientesActivity::class.java)
            startActivity(intentRedirecionarTelaListagemClientes)
        } else if (elementoInterface.id == R.id.btn_pesquisar) {
            // buscar um cliente
            this.buscarCliente()
        } else {
            // deletar um cliente
            this.deletarCliente()
        }

    }

}