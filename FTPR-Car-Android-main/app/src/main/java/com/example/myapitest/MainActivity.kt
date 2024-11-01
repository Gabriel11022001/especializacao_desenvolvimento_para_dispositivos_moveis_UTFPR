package com.example.myapitest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapitest.adapter.CarroAdapter
import com.example.myapitest.databinding.ActivityMainBinding
import com.example.myapitest.model.Carro
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carroAdapter: CarroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestLocationPermission()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        fetchItems()
    }

    private fun setupView() {
        this.binding.recyclerCarros.layoutManager = LinearLayoutManager(this)

        this.carroAdapter = CarroAdapter(this) { idCarro ->
            val intentDetalhesCarro = Intent(this, DetalhesCarroActivity::class.java)
            intentDetalhesCarro.putExtra("id_carro", idCarro)
            startActivity(intentDetalhesCarro)
            finish()
        }

        this.binding.recyclerCarros.adapter = this.carroAdapter
    }

    private fun requestLocationPermission() {

    }

    private fun apresentarNotificacao(sucesso: Boolean, mensagem: String) {

    }

    private fun fetchItems() {

        try {
            val carroApi: ApiCarro = ApiCarro()

            // callbck executado durante o processamento da requisição para listar os carros do servidor
            val onProcessandoRequisicaoConsultarCarros: () -> Unit = {

            }

            // callback executado quando ocorre um erro na requisição
            val onErroRequisicaoConsultarCarros: (String) -> Unit = { mensagemErro ->
                this.apresentarNotificacao(false, mensagemErro)
            }

            // callback executado quando a requisição é processada com sucesso
            val onSucessoRequisicaoConsultarCarros: (ArrayList<Carro>) -> Unit = { carros ->
                val msgAlertaSucesso: String = if (carros.size == 0) "Existe um total de ${ carros.size } cadastrados no sistema."
                else "Não existem carros cadastrados no sistema."

                this.apresentarNotificacao(true, msgAlertaSucesso)

                // apresentar carros na RecyclerView
                this.apresentarCarros(carros)
            }

            carroApi.listarCarros(
                onProcessandoRequisicao = onProcessandoRequisicaoConsultarCarros,
                onErroRequisicao = onErroRequisicaoConsultarCarros,
                onSucessoRequisicao = onSucessoRequisicaoConsultarCarros
            )

        } catch (e: Exception) {
            Toast.makeText(this, "Erro: ${ e.message }", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun apresentarCarros(carros: ArrayList<Carro>) {
        this.carroAdapter.carros = carros
        this.carroAdapter.notifyDataSetChanged()
    }

}
