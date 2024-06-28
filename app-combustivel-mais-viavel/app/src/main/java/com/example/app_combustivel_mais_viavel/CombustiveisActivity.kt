package com.example.app_combustivel_mais_viavel

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CombustiveisActivity : AppCompatActivity() {

    private lateinit var listCombustiveis: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combustiveis)
        // mapear elementos de interface
        this.listCombustiveis = findViewById(R.id.list_combustiveis)
        // maperar evento de clique no elemento da lista
        this.listCombustiveis.setOnItemClickListener { parent, view, position, id ->
            var consumo: Double = 0.0
            var nomeCombustivel: String = ""

            if (position == 0) {
                consumo = 15.0
                nomeCombustivel = "Diesel"
            } else if (position == 1) {
                consumo = 12.0
                nomeCombustivel = "Gasolina"
            } else if (position == 2) {
                consumo = 10.0
                nomeCombustivel = "Diesel Adtivado"
            } else {
                consumo = 8.0
                nomeCombustivel = "Etanol"
            }

            if (intent.getIntExtra("buscar_consumo", 0) == 1) {
                intent.putExtra("consumo_combustivel_1", consumo)
                intent.putExtra("nome_combustivel_1", nomeCombustivel)
            } else {
                intent.putExtra("consumo_combustivel_2", consumo)
                intent.putExtra("nome_combustivel_2", nomeCombustivel)
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        this.listarCombustiveis()
    }

    private fun listarCombustiveis() {
        val combustiveis = listOf(
            "Diesel",
            "Gasolina",
            "Diesel Adtivado",
            "Etanol"
        )
        this.listCombustiveis.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, combustiveis)
    }

}