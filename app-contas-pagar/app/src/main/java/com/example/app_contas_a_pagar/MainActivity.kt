package com.example.app_contas_a_pagar

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.app_contas_a_pagar.dao.LancamentoDAO
import com.example.app_contas_a_pagar.model.Lancamento
import java.util.ArrayList

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var btnLancar: Button
    private lateinit var btnVerLancamentos: Button
    private lateinit var btnSaldo: Button
    private lateinit var spnDetalhe: Spinner
    private lateinit var spnTipo: Spinner
    private lateinit var edtValor: EditText
    private lateinit var edtDataLancamento: EditText
    private lateinit var adapterSpinnerTipo: ArrayAdapter<String>
    private lateinit var adapterSpinnerDetalhe: ArrayAdapter<String>
    private lateinit var lancamentoDAO: LancamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mapear elementos de interface
        this.mapearElementosInterface()

        // mapear eventos
        this.mapearEventos()

        this.lancamentoDAO = LancamentoDAO(this)
    }

    private fun mapearElementosInterface() {
        this.btnLancar = findViewById(R.id.btn_lancar)
        this.btnVerLancamentos = findViewById(R.id.btn_listar_lancamentos)
        this.btnSaldo = findViewById(R.id.btn_saldo)
        this.edtValor = findViewById(R.id.edt_valor)
        this.edtDataLancamento = findViewById(R.id.edt_data_lancamento)
        this.spnTipo = findViewById(R.id.spn_tipo)
        this.spnDetalhe = findViewById(R.id.spn_detalhe)

        // configurar adapter dos spinners
        this.adapterSpinnerTipo = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListOf())
        this.adapterSpinnerDetalhe = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListOf())

        this.adapterSpinnerTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.adapterSpinnerDetalhe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        this.adapterSpinnerTipo.addAll(arrayListOf("Crédito", "Débito"))

        this.spnTipo.adapter = this.adapterSpinnerTipo
        this.spnDetalhe.adapter = this.adapterSpinnerDetalhe
    }

    private fun mapearEventos() {
        this.btnLancar.setOnClickListener(this)
        this.btnVerLancamentos.setOnClickListener(this)
        this.btnSaldo.setOnClickListener(this)

        // mapear evento de seleção da opção de pagamento do lançamento
        this.spnTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val opcao: String = p0!!.selectedItem as String

                if (opcao.lowercase() == "crédito") {
                    // apresentar opções do crédito
                    apresentarOpcoes("crédito")
                } else {
                    // apresentar opções débito
                    apresentarOpcoes("débito")
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }

        }

    }

    private fun apresentarOpcoes(opcao: String) {
        val opcoesDetalhes: ArrayList<String> = ArrayList()

        if (opcao == "crédito") {
            opcoesDetalhes.add("Salário")
            opcoesDetalhes.add("Extras")
        } else {

        }

        // popular spinner de detalhes com as opções
        this.adapterSpinnerDetalhe.clear()
        this.adapterSpinnerDetalhe.addAll(opcoesDetalhes)
    }

    private fun validarFormulario(): Boolean {
        var ok: Boolean = true
        val valor: String = this.edtValor.text.toString().trim()
        val dataLancamento: String = this.edtDataLancamento.text.toString().trim()

        return ok
    }

    private fun cadastrar() {

        try {

            if (this.validarFormulario()) {

            } else {

            }

        } catch (e: Exception) {
            Log.e("erro", "Ocorreu um erro ao tentar-se cadastrar o lançamento: ${ e.message.toString() }")
        }

    }

    override fun onClick(p0: View?) {

        if (p0!!.id == R.id.btn_lancar) {
            // registrar um lançamento de conta a pagar
        } else if (p0!!.id == R.id.btn_listar_lancamentos) {
            // redirecionar a tela para listar os lançamentos
        } else {
            // calcular o saldo
        }

    }

}