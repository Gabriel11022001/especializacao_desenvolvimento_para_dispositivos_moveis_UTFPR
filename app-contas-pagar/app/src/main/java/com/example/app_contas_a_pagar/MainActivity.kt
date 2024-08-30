package com.example.app_contas_a_pagar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.app_contas_a_pagar.dao.LancamentoDAO
import com.example.app_contas_a_pagar.model.Lancamento
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var btnLancar: Button
    private lateinit var btnVerLancamentos: Button
    private lateinit var btnSaldo: Button
    private lateinit var spnDetalhe: Spinner
    private lateinit var spnTipo: Spinner
    private lateinit var edtValor: EditText
    private lateinit var datePickerDataLancamento: DatePicker
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
        this.datePickerDataLancamento = findViewById(R.id.date_picker_data_lancamento)
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
            opcoesDetalhes.add("Alimentação")
            opcoesDetalhes.add("Moradia")
            opcoesDetalhes.add("Saúde")
            opcoesDetalhes.add("Transporte")
        }

        // popular spinner de detalhes com as opções
        this.adapterSpinnerDetalhe.clear()
        this.adapterSpinnerDetalhe.addAll(opcoesDetalhes)
    }

    private fun validarFormulario(): Boolean {
        var ok = true
        val valor: String = this.edtValor.text.toString().trim()
        var msgErro: String = ""

        if (valor.isEmpty()) {
            ok = false
            msgErro = "Informe o valor!"
        } else if (valor.toDouble() <= 0.0) {
            ok = false
            msgErro = "Valor inválido!"
        }

        if (!msgErro.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), msgErro, Snackbar.LENGTH_LONG)
                .show()
        }

        return ok
    }

    private fun cadastrar() {

        try {

            if (this.validarFormulario()) {
                val lancamento: Lancamento = Lancamento()
                val dia = if ((this.datePickerDataLancamento.dayOfMonth) < 10) "0${ this.datePickerDataLancamento.dayOfMonth }" else (this.datePickerDataLancamento.dayOfMonth).toString()
                val mes = if ((this.datePickerDataLancamento.month + 1) < 10) "0${ this.datePickerDataLancamento.month + 1 }" else (this.datePickerDataLancamento.month + 1).toString()
                val ano = this.datePickerDataLancamento.year.toString()
                val dataCompleta = "${ dia }/${ mes }/${ ano }"

                lancamento.valor = this.edtValor.text.toString().trim().toDouble()
                lancamento.pago = false
                lancamento.tipo = this.spnTipo.selectedItem.toString()
                lancamento.detalhe = this.spnDetalhe.selectedItem.toString()
                lancamento.dataLancamento = dataCompleta
                this.lancamentoDAO.salvar(lancamento)
                Snackbar.make(findViewById(android.R.id.content), "Lançamento cadastrado com sucesso!", Snackbar.LENGTH_LONG).show()
                // limpar formulário
                this.edtValor.text.clear()
            }

        } catch (e: Exception) {
            Log.e("erro", "Ocorreu um erro ao tentar-se cadastrar o lançamento: ${ e.message.toString() }")
            Snackbar.make(findViewById(android.R.id.content), "Erro: ${ e.message.toString() }", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun redirecionarTelaListarLancamentos() {
        val intent = Intent(this, LancamentosActivity::class.java)
        startActivity(intent)
    }

    private fun calcularSaldo() {

    }

    override fun onClick(p0: View?) {

        if (p0!!.id == R.id.btn_lancar) {
            // registrar um lançamento de conta a pagar
            this.cadastrar()
        } else if (p0!!.id == R.id.btn_listar_lancamentos) {
            // redirecionar a tela para listar os lançamentos
            this.redirecionarTelaListarLancamentos()
        } else {
            // calcular o saldo
            this.calcularSaldo()
        }

    }

}