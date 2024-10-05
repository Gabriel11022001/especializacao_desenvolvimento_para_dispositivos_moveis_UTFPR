package com.example.app_consumo_retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_consumo_retrofit.adapter.ItemAdapter
import com.example.app_consumo_retrofit.databinding.ActivityMainBinding
import com.example.app_consumo_retrofit.service.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // utilizar o viewBinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * com o viewBinding eu não preciso ficar mapeando os elementos
         * de interface utilizando o método findViewById, eu posso simplesmente
         * acessar os elementos de interface por meio do objeto que representa o
         * viewbinding
         */
        this.binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(this.binding.root)

        // configurar a view
        this.setUpView()
    }

    override fun onResume() {
        super.onResume()
        this.listarItens()
    }

    private fun visualizar(item: Item) {
        val intentDetalhesItem = Intent(this, DetalhesItemActivity::class.java)

        intentDetalhesItem.putExtra("id", item.valor!!.id)
        startActivity(intentDetalhesItem)
        finish()
    }

    private fun setUpView() {
        this.binding.recyclerItens.layoutManager = LinearLayoutManager(this)
        // configurar adapter

        // listener de quando o usuário clicar no item da lista, que será passado para o adapter
        val itemClickListener: (Item) -> Unit = {
            Log.d("id", it.id)
            visualizar(item = it)
        }

        this.itemAdapter = ItemAdapter(this, itemClickListener)
        this.binding.recyclerItens.adapter = this.itemAdapter

        this.binding.btnAdicionarNovoItem.setOnClickListener {
            // redirecionar usuário para a tela de cadastro de item
            startActivity(Intent(this, CadastroItemActivity::class.java))
            finish()
        }

        // evento de realizar o refresh
        this.binding.swRefreshItens.setOnRefreshListener {
            this.binding.swRefreshItens.isRefreshing = true
            this.listarItens()
        }
    }

    private fun listarItens() {

        // fazer uma requisição utilizando corrotina
        CoroutineScope(Dispatchers.IO).launch {
            val retrofitClient = RetrofitClient()
            val apiService = retrofitClient.apiService

            var msg: String = ""
            var resp: List<Item>? = null

            try {
                resp = apiService.listarTodos()
                msg = "Sucesso"
            } catch (e: Exception) {
                msg = e.message.toString()
            }

            // voltando para a thread principal
            withContext(Dispatchers.Main) {
                binding.swRefreshItens.isRefreshing = false

                if (msg == "Sucesso") {

                    if (resp != null) {
                        apresentarItens(resp)
                    }

                } else {
                    Log.d("erro", msg)
                }

            }
        }

    }

    private fun apresentarItens(itens: List<Item>) {

        val itensArrayList = ArrayList<Item>()

        itens.forEach {
            Log.d("id", it.valor!!.id)
            Log.d("nome", it.valor!!.nome)
            Log.d("img", it.valor!!.imagemUrl)
            itensArrayList.add( it )
        }

        this.itemAdapter.setItens( itensArrayList )
    }

}