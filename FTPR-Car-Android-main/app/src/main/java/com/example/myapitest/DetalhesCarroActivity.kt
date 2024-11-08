package com.example.myapitest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapitest.databinding.ActivityDetalhesCarroBinding
import com.example.myapitest.model.Carro
import com.google.android.material.snackbar.Snackbar

class DetalhesCarroActivity : AppCompatActivity() {

    private lateinit var detalhesCarroBinding: ActivityDetalhesCarroBinding
    private var idCarro: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.detalhesCarroBinding = ActivityDetalhesCarroBinding.inflate(layoutInflater)

        setContentView(this.detalhesCarroBinding.root)

        // obter o id_carro passado como parâmetro para a activity
        if (intent.getStringExtra("id_carro")!!.isNotBlank()) {
            this.idCarro = intent.getStringExtra("id_carro")!!
        }

        // mapear eventos de interface
        this.detalhesCarroBinding.btnDeletarCarro.setOnClickListener { this.deletarCarro() }
        this.detalhesCarroBinding.btnEditarCarro.setOnClickListener { this.editarCarro() }

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onResume() {
        super.onResume()

        // obter detalhes do carro por meio de uma requisição para a api no servidor
        this.obterDadosCarro()
    }

    private fun obterDadosCarro() {

        try {

            val onProcessandoRequisicaoBuscarCarroPeloId: () -> Unit = {
                this.apresentarNotificacao(true, "Buscando dados do carro, aguarde...")
            }

            val onErroRequisicaoBuscarCarroPeloId: (String) -> Unit = { msgErro ->
                // Toast.makeText(this, msgErro, Toast.LENGTH_LONG).show()
                // Log.e("erro_buscar_carro", msgErro)
                this.apresentarNotificacao(false, msgErro)
            }

            val onSucessoBuscarCarroPeloId: (Carro) -> Unit = { carro ->
                // Log.d("teste", "trouxe o carro: " + carro.nomeCarro)
                this.apresentarNotificacao(true, "Carro encontrado com sucesso no servidor.")
                this.apresentarDetalhesCarro(carro)
            }

            val carroApi: ApiCarro = ApiCarro()

            carroApi.buscarCarroPeloId(
                this.idCarro,
                onProcessandoRequisicao = onProcessandoRequisicaoBuscarCarroPeloId,
                onErroRequisicao = onErroRequisicaoBuscarCarroPeloId,
                onSucessoRequisicao = onSucessoBuscarCarroPeloId
            )

        } catch (e: Exception) {
            Log.e("erro_buscar_detalhes_carro", e.message.toString())
        }

    }

    private fun apresentarDetalhesCarro(carro: Carro) {
        this.detalhesCarroBinding.txtNomeCarro.text = "Modelo: ${ carro.nomeCarro.uppercase() }"
        this.detalhesCarroBinding.txtLicencaCarro.text = "Licença: ${ carro.licenca }"
        this.detalhesCarroBinding.txtAnoCarro.text = "Ano de lançamento do carro: ${ carro.ano }"
    }

    private fun apresentarNotificacao(sucesso: Boolean, mensagem: String) {
        val snackBarAlerta: Snackbar = Snackbar.make(findViewById(android.R.id.content), mensagem, Snackbar.LENGTH_LONG)

        // definir a cor de fundo
        if (sucesso) {
            snackBarAlerta.setBackgroundTint(getColor(android.R.color.holo_green_dark))
        } else {
            snackBarAlerta.setBackgroundTint(getColor(android.R.color.holo_red_dark))
        }

        // apresentar a snackbar
        snackBarAlerta.show()
    }

    private fun deletarCarro() {

        try {
            val carroApi: ApiCarro = ApiCarro()

            val onProcessandoRequisicaoDeletarCarro: () -> Unit = {
                this.apresentarNotificacao(true, "Deletando o carro, aguarde...")
            }

            val onErroRequisicaoDeletarCarro: (String) -> Unit = { msgErro ->
                this.apresentarNotificacao(false, msgErro)
            }

            val onSucessoRequisicaoDeletarCarro: (String) -> Unit = { msgSucesso ->
                this.apresentarNotificacao(true, msgSucesso)
                this.retornar()
            }

            carroApi.deletarCarro(this.idCarro, onProcessandoRequisicaoDeletarCarro, onErroRequisicaoDeletarCarro, onSucessoRequisicaoDeletarCarro)

        } catch (e: Exception) {
            this.apresentarNotificacao(false, "Erro: ${ e.message }")
        }

    }

    private fun editarCarro() {
        val intent: Intent = Intent(this, CadastroCarroActivity::class.java)
        intent.putExtra("id_carro", this.idCarro)
        startActivity(intent)
        finish()
    }

    private fun retornar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        this.retornar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // retornar
        if (item.itemId == android.R.id.home) {
            this.retornar()

            return true
        }

        return super.onOptionsItemSelected(item)
    }

}