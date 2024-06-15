package com.example.calculadora_imc_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private var btnLimpar: Button? = null
    private var btnCalcularImc: Button? = null
    private var txtImc: TextView? = null
    private var edtPeso: EditText? = null
    private var edtAltura: EditText? = null
    private val calculadoraImc: CalculadoraIMC = CalculadoraIMC()

    /**
     * o método onCreate() está sobreescrebendo o método
     * onCreate() da classe AppCompactActivity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        this.mapearElementosInterface()
        this.mapearEventos()
    }

    private fun mapearElementosInterface() {
        this.txtImc = findViewById(R.id.txt_imc)
        this.btnLimpar = findViewById(R.id.btn_limpar)
        this.btnCalcularImc = findViewById(R.id.btn_calcular_imc)
        this.edtPeso = findViewById(R.id.edt_peso)
        this.edtAltura = findViewById(R.id.edt_altura)
    }

    private fun mapearEventos() {
        this.btnCalcularImc!!.setOnClickListener(this)
        this.btnLimpar!!.setOnClickListener(this)
    }

    private fun limpar() {
        // o método clear vai limpar os campos
        this.edtPeso!!.text.clear()
        this.edtAltura!!.text.clear()
        this.txtImc!!.text = "0.0"
    }

    private fun validarFormulario(): Boolean {
        var ok: Boolean = true

        if (this.edtPeso!!.text.toString().trim() == "") {
            ok = false
            this.edtPeso!!.error = "Informe o peso!"
        } else if (this.edtPeso!!.text.toString().toDouble() <= 0.0) {
            ok = false
            this.edtPeso!!.error = "Peso inválido!"
        }

        if (this.edtAltura!!.text.toString().trim() == "") {
            ok = false
            this.edtAltura!!.error = "Informe a altura!"
        } else if (this.edtAltura!!.text.toString().toDouble() <= 0) {
            ok = false
            this.edtAltura!!.error = "Altura inválida!"
        }

        return ok
    }

    private fun calcularImc() {
        Log.i("teste", "Caiu no método para calcular o imc!")
        try {

            if (this.validarFormulario()) {
                // realizar o calculo do imc
                val peso: Double = this.edtPeso!!.text.trim().toString().toDouble()
                val altura: Double = this.edtAltura!!.text.trim().toString().toDouble()
                val imc: Double = this.calculadoraImc.calcular(peso, altura)
                this.txtImc!!.text = imc.toString()
            } else {
                Log.e("erro_calcular_imc", "Ocorreu um erro ao tentar-se calcular o imc!")
                Toast.makeText(applicationContext, "Erros de validação de dados!", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Log.e("erro", e.message.toString() + " - " + e.stackTraceToString())
        }

    }

    override fun onClick(p0: View?) {

        if (p0!!.id == this.btnCalcularImc!!.id) {
            Log.d("teste", "O usuário clicou no botão para calcular o imc!")
            // o usuário clicou no botão para calcular o imc
            this.calcularImc()
        } else {
            // o usuário clicou no botão para limpar
            this.limpar()
        }

    }

}