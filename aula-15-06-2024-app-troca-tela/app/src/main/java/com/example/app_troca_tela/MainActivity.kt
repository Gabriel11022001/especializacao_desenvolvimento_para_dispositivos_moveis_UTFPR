package com.example.app_troca_tela

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mapeando o botão de lançamento
        val btnLancamento: Button = findViewById(R.id.btn_lancamento)
        // mapeando o evento de clique desse botão
        btnLancamento.setOnClickListener() {
            redirecionar()
        }
    }

    private fun redirecionar() {
        Log.d("teste", "O usuário clicou no botão de lançamento!")
        val intentRedirecionarTelaRealizarLancamento = Intent(applicationContext, LancamentoActivity::class.java)
        startActivity(intentRedirecionarTelaRealizarLancamento)
    }

}