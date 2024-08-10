package com.example.app_persistencia_dados_listagem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.app_persistencia_dados_listagem.dao.ClienteDAO
import com.example.app_persistencia_dados_listagem.listener.IOnEventoClickListener
import com.example.app_persistencia_dados_listagem.model.Cliente
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ClientesActivity : AppCompatActivity() {

    // private lateinit var lvClientes: ListView
    private lateinit var btnAdicionarNovoCliente: ExtendedFloatingActionButton
    private var clientes: ArrayList<Cliente> = ArrayList()
    private var nomesClientes: ArrayList<String> = arrayListOf()
    // private lateinit var clienteAdapter: ArrayAdapter<String>
    private lateinit var recyclerClientes: RecyclerView
    private lateinit var eventoClickListener: IOnEventoClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)
        Log.d("teste", "O usuário foi redirecionado para a tela de listagem de clientes!")
        // mapear elementos de interface
        // this.lvClientes = findViewById(R.id.lv_clientes)
        this.recyclerClientes = findViewById(R.id.recycler_clientes)
        this.btnAdicionarNovoCliente = findViewById(R.id.btn_adicionar_novo_cliente)

        this.btnAdicionarNovoCliente.setOnClickListener {
            // evento de redirecionar o usuário para a tela de cadastro de cliente
            val intentCadastrarCliente: Intent = Intent(applicationContext, CadastroClienteActivity::class.java)
            startActivity(intentCadastrarCliente)
        }

        // configurar evento de clique sobre o adapter da RecyclerView
        this.eventoClickListener = object : IOnEventoClickListener {

            override fun clickElementoLista(idCliente: Int) {
                // redirecionar usuário para a activity para editar o cliente

                try {
                    val cliente: Cliente = ClienteDAO(applicationContext).buscarPeloId(idCliente)
                    val bundle: Bundle = Bundle()
                    val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                    // preparando o bundle
                    bundle.putInt("id_cliente", cliente.id)
                    bundle.putString("nome_cliente", cliente.nome)
                    bundle.putString("email_cliente", cliente.email)
                    intent.putExtra("dados_cliente_editar", bundle)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Log.e("erro", e.message.toString())
                    Toast.makeText(applicationContext, "Erro ao tentar-se consultar o cliente!", Toast.LENGTH_LONG).show()
                }

            }

        }

        this.configurarLista()
    }

    override fun onStart() {
        super.onStart()
        // consultar clientes no banco local do app
        this.consultarClientes()
    }

    private fun configurarLista() {
        // configurar o adapter
        /*this.clienteAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            this.nomesClientes
        )*/

        // this.lvClientes.adapter = this.clienteAdapter

        // configurar o adapter da RecyclerView

        // configurar a RecyclerView
    }

    private fun consultarClientes() {

        try {
            val clienteDAO: ClienteDAO = ClienteDAO(contexto = applicationContext)
            this.clientes = ArrayList()
            this.clientes = clienteDAO.listarTodos()

            if (this.clientes.size == 0) {
                Toast.makeText(applicationContext, "Não existem clientes cadastrados no banco de dados!", Toast.LENGTH_LONG).show()
            } else {

                this.clientes.forEach {
                    this.nomesClientes.add(it.nome)
                }

                // passando dados para o adapter
                // this.clienteAdapter.addAll(this.nomesClientes)
                // notificando o adapter que houve alteração nos dados
                // this.clienteAdapter.notifyDataSetChanged()
            }

        } catch (e: Exception) {
            Log.e("erro_consultar_clientes", e.message.toString())
        }

    }

}