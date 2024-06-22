package com.example.app_troca_tela

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.internal.Ref.DoubleRef

class ConfirmaLancamentoActivity : AppCompatActivity() {

    private lateinit var txtCodigo: TextView
    private lateinit var txtQuantidade: TextView
    private lateinit var txtPreco: TextView
    private lateinit var txtValorTotalVenda: TextView
    private lateinit var btnConfirmar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirma_lancamento)
        // mapear elementos de interface
        this.txtCodigo = findViewById(R.id.txt_codigo)
        this.txtPreco = findViewById(R.id.txt_preco_produto)
        this.txtQuantidade = findViewById(R.id.txt_quantidade_unidades)
        this.btnConfirmar = findViewById(R.id.btn_confirmar)
        this.txtValorTotalVenda = findViewById(R.id.txt_valor_total_venda)
        // mapeando evento de clique no botão confirmar
        this.btnConfirmar.setOnClickListener() {
            confirmar()
        }
    }

    override fun onStart() {
        super.onStart()
        this.apresentarDadosLancamento()
    }

    private fun confirmar() {
        val corpoSms: String = "Código do produto: ${ this.txtCodigo.text } | Quantidade: ${ this.txtQuantidade.text } | Preço do produto: R$" + this.txtPreco.text
        val numeroEnviar: String = "sms: +5514997967030"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(numeroEnviar))
        intent.putExtra("sms_body", corpoSms)

        startActivity(intent)
    }

    private fun apresentarDadosLancamento() {
        val quantidade: Int = intent.getIntExtra("quantidade", 0)
        val preco: Double = intent.getDoubleExtra("preco_produto", 0.0)
        val codigo: Int = intent.getIntExtra("codigo", 0)
        this.txtPreco.text = preco.toString()
        this.txtCodigo.text = codigo.toString()
        this.txtQuantidade.text = quantidade.toString()
    }

}