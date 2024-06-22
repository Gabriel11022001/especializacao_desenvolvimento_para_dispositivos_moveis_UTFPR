package com.example.app_troca_tela

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProdutosActivity : AppCompatActivity() {

    private lateinit var listaProdutos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produtos)
        // mapear elementos de interface
        this.listaProdutos = findViewById(R.id.list_view_produtos)
        // mapear evento de clique no ListView
        this.listaProdutos.setOnItemClickListener { parent, view, position, id ->
            val textoElementoClicado = listaProdutos.getItemAtPosition(position).toString()
            Log.d("produto_selecionado", textoElementoClicado)
            val idProdutoSelecionado: Int = position + 1
            intent.putExtra("id_produto_selecionado", idProdutoSelecionado)
            setResult(RESULT_OK, intent)
            // matar a intent(remover ela da pilha de execução)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        this.listarProdutos()
    }

    private fun listarProdutos() {
        Log.d("teste", "Caiu no método para fazer a listagem dos produtos!")
        val produtos = listOf(
            "Coca-Cola de 2 litros",
            "Bolo de Chocolate",
            "Fanta Uva",
            "Bolo de Nata",
            "Bolo de Leite e Ninho",
            "Copo de Café de 100ML"
        )
        this.listaProdutos.adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, produtos)
    }

}