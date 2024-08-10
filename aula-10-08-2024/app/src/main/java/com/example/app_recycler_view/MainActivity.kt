package com.example.app_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_recycler_view.adapter.MotivacaoAdapter
import com.example.app_recycler_view.dao.MotivacaoDAO
import com.example.app_recycler_view.model.Motivacao

class MainActivity : AppCompatActivity() {

    private var motivacoes: ArrayList<Motivacao> = ArrayList()
    private lateinit var motivacaoDAO: MotivacaoDAO
    private lateinit var recyclerMotivacoes: RecyclerView
    private lateinit var motivacaoAdapter: MotivacaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.motivacaoDAO = MotivacaoDAO(this)

        // mapeando elementos de interface
        this.recyclerMotivacoes = findViewById(R.id.recycler_motivacoes)

        // mapeando eventos

        // configurando o meu adapter e a RecyclerView
        this.motivacaoAdapter = MotivacaoAdapter(this)
        this.recyclerMotivacoes.layoutManager = LinearLayoutManager(this)
        this.recyclerMotivacoes.adapter = this.motivacaoAdapter
    }

    override fun onStart() {
        super.onStart()
        // buscar motivacoes
        this.motivacoes = motivacaoDAO.buscarTodasMotivacoes()
        // atualizar e notificar para o adapter que houve alteração nos dados
        this.motivacaoAdapter.setDados(this.motivacoes)
    }

}