package com.example.calculadora_gorjeta

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var edtValorTotal: EditText
    private lateinit var rbOtimo: RadioButton
    private lateinit var rbBom: RadioButton
    private lateinit var rbMaisOuMenos: RadioButton
    private lateinit var swArredondar: Switch
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.mapearElementosInterface()
        this.mapearEventoClique()

        /**
         * obtendo os dados salvos na savedInstanceState
         */
        if (savedInstanceState != null && savedInstanceState.containsKey("valor_gorjeta_calculado")) {
            this.txtResultado.text = savedInstanceState.getString("valor_gorjeta_calculado", "")
        }

    }

    private fun mapearElementosInterface() {
        this.edtValorTotal = findViewById(R.id.edt_valor_total)
        this.btnCalcular = findViewById(R.id.btn_calcular)
        this.rbOtimo = findViewById(R.id.rd_otimo)
        this.rbBom = findViewById(R.id.rd_bom)
        this.rbMaisOuMenos = findViewById(R.id.rd_mais_ou_menos)
        this.swArredondar = findViewById(R.id.sw_arredondar_para_cima)
        this.txtResultado = findViewById(R.id.txt_resultado)
    }

    private fun mapearEventoClique() {
        this.btnCalcular.setOnClickListener(this)
    }

    private fun realizarCalculo() {

        if (this.validarCampoValorTotal()) {
            val valorTotal: Double = this.edtValorTotal.text.toString().toDouble()
            val arredondar: Boolean = this.swArredondar.isChecked
            var percentualGorjeta: Double = 0.0

            if (this.rbOtimo.isChecked) {
                percentualGorjeta = 20.0
            } else if (this.rbBom.isChecked) {
                percentualGorjeta = 15.0
            } else {
                percentualGorjeta = 10.0
            }

            var gorjeta: Double = CalculadoraGorjeta.calcular(valorTotal, percentualGorjeta)
            Log.d("gorjeta:", gorjeta.toString())

            if (arredondar) {
                gorjeta = ceil(gorjeta)
            }

            this.txtResultado.text = "Gorjeta: ${ gorjeta }"
            // limpar o campo
            this.edtValorTotal.text.clear()
            Toast.makeText(applicationContext, "Calculo realizado com sucesso!", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun validarCampoValorTotal(): Boolean {

        if (this.edtValorTotal.text.toString().trim().isEmpty()) {
            this.edtValorTotal.error = "Informe o valor total"

            return false
        }

        if (this.edtValorTotal.text.toString().toDouble() <= 0) {
            this.edtValorTotal.error = "Valor inválido!"

            return false
        }

        return true
    }

    override fun onClick(p0: View?) {
        this.realizarCalculo()
    }

    /**
     * quando o usuário rotaciona a tela, o método onCreate é invocado
     * novamente, então é necessário salvar os dados em um bundle para que
     * os mesmos não sejam perdidos
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("valor_gorjeta_calculado", this.txtResultado.text.toString())
    }

}