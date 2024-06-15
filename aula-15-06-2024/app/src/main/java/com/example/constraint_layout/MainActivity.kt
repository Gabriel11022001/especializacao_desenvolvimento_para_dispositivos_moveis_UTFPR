package com.example.constraint_layout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), OnClickListener {

    // mapeando os elementos de interface
    // lateinit -> vou poder atribuir um valor a variável depois
    private lateinit var edtPeso: EditText
    private lateinit var edtAltura: EditText
    private lateinit var txtImc: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button
    // propriedade representando o contexto do app
    private lateinit var contexto: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.contexto = applicationContext
        this.mapearElementosInterface()
        this.mapearEventos()
    }

    private fun mapearElementosInterface() {
        this.edtPeso = findViewById(R.id.edt_peso)
        this.edtAltura = findViewById(R.id.edt_altura)
        this.txtImc = findViewById(R.id.txt_imc_calculado)
        this.btnCalcular = findViewById(R.id.btn_calcular)
        this.btnLimpar = findViewById(R.id.btn_limpar)
    }

    private fun mapearEventos() {
        this.btnCalcular.setOnClickListener(this)
        this.btnLimpar.setOnClickListener(this)
    }

    private fun validar(): Boolean {
        var camposOk = true

        if (this.edtPeso.text.toString().trim().isEmpty()) {
            camposOk = false
            this.edtPeso.error = "Informe o peso!"
        } else if (this.edtPeso.text.toString().toDouble() <= 0) {
            camposOk = false
            this.edtPeso.error = "Peso inválido!"
        }

        if (this.edtAltura.text.toString().trim().isEmpty()) {
            camposOk = false
            this.edtAltura.error = "Informe a altura!"
        } else if (this.edtAltura.text.toString().toDouble() <= 0) {
            camposOk = false
            this.edtAltura.error = "Altura inválida!"
        }

        return camposOk
    }

    private fun calcularImc() {
        val peso: Double = this.edtPeso.text.toString().toDouble()
        val altura: Double = this.edtAltura.text.toString().toDouble()

        // -> Locale.getDefault().language obter a linguagem definida no dispositivo
        if (Locale.getDefault().language.equals("PT")) {
            this.calcularImcParaPortugues(peso, altura)
        } else {
            this.calcularImcParaIngles(peso, altura)
        }

    }

    private fun calcularImcParaPortugues(peso: Double, altura: Double) {
        val imc: Double = peso / (altura * altura)
        this.txtImc.text = imc.toString().replace(".", ",")
    }

    private fun calcularImcParaIngles(peso: Double, altura: Double) {
        val imc: Double = peso / (altura * altura)
        this.txtImc.text = imc.toString()
    }

    private fun limpar() {
        this.edtPeso.text.clear()
        this.edtAltura.text.clear()
        this.txtImc.text = getString(R.string.txt_padrao_imc)
    }

    private fun apresentarAlerta(mensagem: String) {
        Toast.makeText(this.contexto, mensagem, Toast.LENGTH_LONG)
            .show()
    }

    override fun onClick(p0: View?) {

        if (p0!!.id == R.id.btn_calcular) {
            // o usuário clicou no botão para calcular o imc

            if (this.validar()) {
                this.calcularImc()
            } else {
                // getString() -> retorna uma string com o texto do arquivo strings.xml
                this.apresentarAlerta(getString(R.string.alerta_erro))
            }

        } else {
            // o usuário clicou no botão para limpar
            this.limpar()
        }

    }

}