package com.example.app_troca_tela

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class LancamentoActivity : AppCompatActivity(), OnClickListener {

    private lateinit var btnRealizarLancamento: Button
    private lateinit var btnListarLancamentos: Button
    private lateinit var edtCodigoProduto: EditText
    private lateinit var edtQuantidadeUniadadesVender: EditText
    private lateinit var edtPrecoProduto: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lancamento)
        // mapeando os elementos de interface
        this.btnRealizarLancamento = findViewById(R.id.btn_realizar_lancamento)
        this.btnListarLancamentos = findViewById(R.id.btn_listar_lancamentos)
        this.edtPrecoProduto = findViewById(R.id.edt_preco)
        this.edtCodigoProduto = findViewById(R.id.edt_codigo_produto)
        this.edtQuantidadeUniadadesVender = findViewById(R.id.edt_quantidade_unidades)
        // mapeando os eventos de clique
        this.btnRealizarLancamento.setOnClickListener(this)
        this.btnListarLancamentos.setOnClickListener(this)
    }

    private fun validarFormulario(): Boolean {
        var formOk: Boolean = true

        if (this.edtPrecoProduto.text.toString().isEmpty()) {
            formOk = false
            this.edtPrecoProduto.error = "Informe o preço!"
        } else if (this.edtPrecoProduto.text.toString().toDouble() <= 0.0) {
            formOk = false
            this.edtPrecoProduto.error = "Preço inválido!"
        }

        if (this.edtCodigoProduto.text.toString().isEmpty()) {
            formOk = false
            this.edtCodigoProduto.error = "Informe o código do produto!"
        } else if (this.edtCodigoProduto.text.toString().toDouble() <= 0.0) {
            formOk = false
            this.edtCodigoProduto.error = "Código inválido!"
        }

        if (this.edtQuantidadeUniadadesVender.text.toString().isEmpty()) {
            formOk = false
            this.edtQuantidadeUniadadesVender.error = "Informe a quantidade de unidades!"
        } else if (this.edtQuantidadeUniadadesVender.text.toString().toDouble() <= 0.0) {
            formOk = false
            this.edtQuantidadeUniadadesVender.error = "Quantidade de unidades inválida!"
        }

        return formOk
    }

    private fun cadastrarLancamento() {

        try {

            if (validarFormulario()) {
                val intentConfirmar = Intent(applicationContext, ConfirmaLancamentoActivity::class.java)
                var codigo: Int = this.edtCodigoProduto.text.toString().toInt()
                var quantiadadeUnidades: Int = this.edtQuantidadeUniadadesVender.text.toString().toInt()
                var preco: Double = this.edtPrecoProduto.text.toString().toDouble()
                // passar dados dessa tela para a tela de confirmar
                intentConfirmar.putExtra("codigo", codigo)
                intentConfirmar.putExtra("quantidade", quantiadadeUnidades)
                intentConfirmar.putExtra("preco_produto", preco)
                startActivity(intentConfirmar)
            }

        } catch (e: Exception) {
            Log.e("erro", "Ocorreu o seguinte erro: ${ e.message }")
        }

    }

    private fun listarProdutos() {
        val intentListarProdutos: Intent = Intent(applicationContext, ProdutosActivity::class.java)
        getResultProdutoSelecionado.launch(intentListarProdutos)
    }

    private val getResultProdutoSelecionado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == RESULT_OK) {

            if (it.data != null) {
                val codigoProduto: Int = it.data?.getIntExtra("id_produto_selecionado", 0)!!.toInt()
                Toast.makeText(applicationContext, "Código: ${ codigoProduto }", Toast.LENGTH_LONG)
                    .show()
                edtCodigoProduto.setText(codigoProduto.toString())
            }

        }

    }

    override fun onClick(p0: View?) {

        if (p0!!.id == this.btnRealizarLancamento.id) {
            // o usuário clicou no botão para realizar um novo lançamento
            this.cadastrarLancamento()
        } else {
            // o usuário clicou no botão para listar todos os produtos
            this.listarProdutos()
        }

    }

}