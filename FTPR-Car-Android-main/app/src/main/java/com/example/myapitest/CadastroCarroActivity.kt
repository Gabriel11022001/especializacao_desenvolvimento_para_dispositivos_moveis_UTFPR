package com.example.myapitest

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapitest.databinding.ActivityCadastroCarroBinding
import com.example.myapitest.model.Carro
import com.example.myapitest.model.Posicao
import com.google.android.material.snackbar.Snackbar

class CadastroCarroActivity : AppCompatActivity() {

    private lateinit var cadastroCarroBinding: ActivityCadastroCarroBinding
    private var idCarro: String = ""
    private lateinit var builderLoader: AlertDialog.Builder
    private lateinit var loader: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.cadastroCarroBinding = ActivityCadastroCarroBinding.inflate(layoutInflater)

        setContentView(this.cadastroCarroBinding.root)

        val idCarro: String = intent.getStringExtra("id_carro") ?: ""

        if (idCarro != "") {
            // editar o carro
            this.idCarro = idCarro
        }

        this.cadastroCarroBinding.btnSalvar.setOnClickListener { this.salvar() }

        this.builderLoader = AlertDialog.Builder(this)
        this.builderLoader.setCancelable(false)
        this.loader = this.builderLoader.create()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        if (this.idCarro != "") {
            // buscar carro pelo id
            this.buscarCarroPeloId()
        }

    }

    private fun buscarCarroPeloId() {

        val onRealizandoRequisicao: () -> Unit = {
            this.apresentarLoader("Consultando carro no servidor, aguarde...")
        }

        val onErroRequisicao: (String) -> Unit = { msgErro ->
            this.esconderLoader()
            this.apresentarAlertaErro(msgErro)
        }

        val onSucessoRequisicao: (Carro) -> Unit = { carro ->
            this.esconderLoader()
            this.apresentarAlertaSucesso("Carro encontrado com sucesso no servidor.")

            this.cadastroCarroBinding.edtNomeCarro.setText(carro.nomeCarro)
            this.cadastroCarroBinding.edtLicencaCarro.setText(carro.licenca)
            this.cadastroCarroBinding.edtAnoCarro.setText(carro.ano)
            this.cadastroCarroBinding.edtUrlFotoCarro.setText(carro.urlFotoCarro)
        }

        val apiCarro = ApiCarro()

        apiCarro.buscarCarroPeloId(this.idCarro, onRealizandoRequisicao, onErroRequisicao, onSucessoRequisicao)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            this.retornar()

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun apresentarAlertaErro(msg: String) {
        val snackBar: Snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(getColor(android.R.color.holo_red_dark))
        snackBar.setTextColor(getColor(R.color.white))

        snackBar.show()
    }

    private fun apresentarAlertaSucesso(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun apresentarLoader(msg: String) {
        this.loader.setMessage(msg)
        this.loader.show()
    }

    private fun esconderLoader() {
        this.loader.dismiss()
    }

    private fun validarCampos(): Boolean {
        val nome: String = this.cadastroCarroBinding.edtNomeCarro.text.toString().trim()
        val licenca: String = this.cadastroCarroBinding.edtLicencaCarro.text.toString().trim()
        val ano: String = this.cadastroCarroBinding.edtAnoCarro.text.toString().trim()
        val url: String = this.cadastroCarroBinding.edtUrlFotoCarro.text.toString().trim()

        return if (nome.isBlank()) {
            this.apresentarAlertaErro("Informe o nome do carro.")

            false
        } else if (nome.length < 2) {
            this.apresentarAlertaErro("O nome do carro deve ter no mínimo 2 caracteres.")

            false
        } else if (licenca.isBlank()) {
            this.apresentarAlertaErro("Informe a licença do carro.")

            false
        } else if (ano.isBlank()) {
            this.apresentarAlertaErro("Informe o ano de lançamento do carro.")

            false
        } else if (ano.toInt() <= 0 || ano.toInt() > 2025) {
            this.apresentarAlertaErro("O ano de lançamento do carro está incorreto.")

            false
        } else if (url.isBlank()) {
            this.apresentarAlertaErro("Informe a url da foto do carro.")

            false
        } else {

            true
        }
    }

    private fun cadastrarCarro() {

        if (this.validarCampos()) {
            // realizar requisição para o servidor para cadastrar o carro

            val onRealizandoRequisicao: () -> Unit = {
                // apresentar loader
                this.apresentarLoader("Enviando dados do carro para o servidor, aguarde...")
            }

            val onErroRequisicao: (String) -> Unit = { msgErro ->
                this.esconderLoader()
                this.apresentarAlertaErro(msgErro)
            }

            val onSucessoRequisicao: (String) -> Unit = { msgSucesso ->
                this.esconderLoader()
                this.apresentarAlertaSucesso(msgSucesso)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            val carroApi: ApiCarro = ApiCarro()

            val carroCadastrar: Carro = Carro(
                urlFotoCarro = this.cadastroCarroBinding.edtUrlFotoCarro.text.toString().trim(),
                nomeCarro = this.cadastroCarroBinding.edtNomeCarro.text.toString().trim(),
                ano = this.cadastroCarroBinding.edtAnoCarro.text.toString().trim(),
                licenca = this.cadastroCarroBinding.edtLicencaCarro.text.toString().trim(),
                id = Utils.encryptStringWithTimestamp(this.cadastroCarroBinding.edtNomeCarro.text.toString().trim()),
                posicao = Posicao(0.0, 0.0)
            )

            carroApi.cadastrarCarro(carroCadastrar, onRealizandoRequisicao, onErroRequisicao, onSucessoRequisicao)
        }

    }

    private fun editarCarro() {

        if (this.validarCampos()) {
            // realizar requisição para o servidor para editar o carro

            val onRealizandoRequisicao: () -> Unit = {
                // apresentar loader
                this.apresentarLoader("Enviando dados do carro para o servidor para edição, aguarde...")
            }

            val onErroRequisicao: (String) -> Unit = { msgErro ->
                this.esconderLoader()
                this.apresentarAlertaErro(msgErro)
            }

            val onSucessoRequisicao: (String) -> Unit = { msgSucesso ->
                this.esconderLoader()
                this.apresentarAlertaSucesso(msgSucesso)
                val intent: Intent = Intent(this, DetalhesCarroActivity::class.java)
                intent.putExtra("id_carro", this.idCarro)
                startActivity(intent)
                finish()
            }

            val carroApi: ApiCarro = ApiCarro()

            val carroEditar: Carro = Carro(
                urlFotoCarro = this.cadastroCarroBinding.edtUrlFotoCarro.text.toString().trim(),
                nomeCarro = this.cadastroCarroBinding.edtNomeCarro.text.toString().trim(),
                ano = this.cadastroCarroBinding.edtAnoCarro.text.toString().trim(),
                licenca = this.cadastroCarroBinding.edtLicencaCarro.text.toString().trim(),
                id = this.idCarro,
                posicao = Posicao(0.0, 0.0)
            )

            carroApi.editarCarro(carroEditar, onRealizandoRequisicao, onErroRequisicao, onSucessoRequisicao)

        }

    }

    private fun salvar() {

        if (this.idCarro == "") {
            // cadastrar um carro novo
            this.cadastrarCarro()
        } else {
            // editar o carro
            this.editarCarro()
        }

    }

    override fun onBackPressed() {
        this.retornar()
    }

    private fun retornar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}