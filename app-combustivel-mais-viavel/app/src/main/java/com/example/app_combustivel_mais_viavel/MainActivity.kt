package com.example.app_combustivel_mais_viavel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button
    private lateinit var btnConsultarConsumoCombustivel1: Button
    private lateinit var btnConsultarConsumoCombustivel2: Button
    private lateinit var edtConsumcCombustivel1: EditText
    private lateinit var edtConsumoCombustivel2: EditText
    private lateinit var edtPrecoCombustivel1: EditText
    private lateinit var edtPrecoCombustivel2: EditText
    private lateinit var txtLabelConsumoCombustivel1: TextView
    private lateinit var txtLabelConsumoCombustivel2: TextView
    private lateinit var txtLabelPrecoCombustivel1: TextView
    private lateinit var txtLabelPrecoCombustivel2: TextView
    private lateinit var txtCombustivelMaisRentavel: TextView
    private var combustivel1: String = ""
    private var combustivel2: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mapear elementos de interface
        this.mapearElementosInterface()
        // mapear eventos
        this.mapearEventos()
    }

    private fun mapearElementosInterface() {
        this.btnCalcular = findViewById(R.id.btn_calcular)
        this.btnLimpar = findViewById(R.id.btn_limpar)
        this.btnConsultarConsumoCombustivel1 = findViewById(R.id.btn_consultar_consumo_combustivel_1)
        this.btnConsultarConsumoCombustivel2 = findViewById(R.id.btn_consultar_consumo_combustivel_2)
        this.edtConsumcCombustivel1 = findViewById(R.id.edt_consumo_combustivel_1)
        this.edtConsumoCombustivel2 = findViewById(R.id.edt_consumo_combustivel_2)
        this.edtPrecoCombustivel1 = findViewById(R.id.edt_preco_combustivel_1)
        this.edtPrecoCombustivel2 = findViewById(R.id.edt_preco_combustivel_2)
        this.txtLabelConsumoCombustivel1 = findViewById(R.id.txt_label_consumo_combustivel_1)
        this.txtLabelConsumoCombustivel2 = findViewById(R.id.txt_label_consumo_combustivel_2)
        this.txtLabelPrecoCombustivel1 = findViewById(R.id.txt_label_preco_combustivel_1)
        this.txtLabelPrecoCombustivel2 = findViewById(R.id.txt_label_preco_combustivel_2)
        this.txtCombustivelMaisRentavel = findViewById(R.id.txt_combustivel_mais_viavel)
    }

    private fun mapearEventos() {
        this.btnCalcular.setOnClickListener(this)
        this.btnLimpar.setOnClickListener(this)
        this.btnConsultarConsumoCombustivel2.setOnClickListener(this)
        this.btnConsultarConsumoCombustivel1.setOnClickListener(this)
    }

    private fun calcularCombustivelMaisRentavel() {

        if (this.validarCampos()) {
            val consumoCombustivel1: Double = this.edtConsumcCombustivel1.text.toString().toDouble()
            val consumoCombustivel2: Double = this.edtConsumoCombustivel2.text.toString().toDouble()
            val precoCombustivel1: Double = this.edtPrecoCombustivel1.text.toString().toDouble()
            val precoCombustivel2: Double = this.edtPrecoCombustivel2.text.toString().toDouble()
            var combustivelMaisRentavel: String = ""
            val resultadoCombustivel1: Double = precoCombustivel1 / consumoCombustivel1
            val resultadoCombustivel2: Double = precoCombustivel2 / consumoCombustivel2

            if (resultadoCombustivel1 < resultadoCombustivel2) {
                combustivelMaisRentavel = this.combustivel1
            } else if (resultadoCombustivel2 < resultadoCombustivel1) {
                combustivelMaisRentavel = this.combustivel2
            } else {
                combustivelMaisRentavel = "Pode abastecer com qualquer um dos dois!"
            }

            this.txtCombustivelMaisRentavel.text = "Combustível mais rentável: ${ combustivelMaisRentavel }"
        } else {
            Toast.makeText(applicationContext, "Erros de validação de campos!", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun limpar() {
        this.edtPrecoCombustivel2.text.clear()
        this.edtPrecoCombustivel1.text.clear()
        this.edtConsumcCombustivel1.text.clear()
        this.edtConsumoCombustivel2.text.clear()
        this.txtCombustivelMaisRentavel.text = getString(R.string.txt_combustivel_mais_viavel)
        this.txtLabelPrecoCombustivel1.text = getString(R.string.txt_preco_combustivel_1)
        this.txtLabelPrecoCombustivel2.text = getString(R.string.txt_preco_combustivel_2)
        this.txtLabelConsumoCombustivel1.text = getString(R.string.text_label_consumo_combustivel_1)
        this.txtLabelConsumoCombustivel2.text = getString(R.string.text_label_consumo_combustivel_2)
    }

    private fun validarCampos(): Boolean {
        var ok: Boolean = true
        val precoCombustivel1 = this.edtPrecoCombustivel1.text.toString()
        val precoCombustivel2 = this.edtPrecoCombustivel2.text.toString()
        val consumoCombustivel1 = this.edtConsumcCombustivel1.text.toString()
        val consumoCombustivel2 = this.edtConsumoCombustivel2.text.toString()

        if (precoCombustivel1.isEmpty()) {
            this.edtPrecoCombustivel1.error = "Campo obrigatório!"
            ok = false
        } else if (precoCombustivel1.toDouble() <= 0.0) {
            this.edtPrecoCombustivel1.error = "Preço inválido!"
            ok = false
        }

        if (precoCombustivel2.isEmpty()) {
            ok = false
            this.edtPrecoCombustivel2.error = "Campo obrigatório!"
        } else if (precoCombustivel2.toDouble() <= 0.0) {
            ok = false
            this.edtPrecoCombustivel2.error = "Preço inválido!"
        }

        if (consumoCombustivel1.isEmpty()) {
            ok = false
            this.edtConsumcCombustivel1.error = "Campo obrigatório!"
        } else if (consumoCombustivel1.toDouble() <= 0.0) {
            ok = false
            this.edtConsumcCombustivel1.error = "Consumo inválido!"
        }

        if (consumoCombustivel2.isEmpty()) {
            ok = false
            this.edtConsumoCombustivel2.error = "Campo obrigatório!"
        } else if (consumoCombustivel2.toDouble() <= 0.0) {
            ok = false
            this.edtConsumoCombustivel2.error = "Consumo inválido!"
        }

        return ok
    }

    private val getResultadoConsumoCombustivel = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == RESULT_OK) {

            if (it.data != null) {

                if (it.data?.getDoubleExtra("consumo_combustivel_1", 0.0)!!.toDouble() != 0.0) {
                    val consumo: Double = it.data?.getDoubleExtra("consumo_combustivel_1", 0.0)!!.toDouble()
                    val nome: String = it.data?.getStringExtra("nome_combustivel_1")!!.toString()
                    edtConsumcCombustivel1.setText(consumo.toString())
                    txtLabelConsumoCombustivel1.text = "Consumo do combustível ${ nome }"
                    txtLabelPrecoCombustivel1.text = "Preço do combustível ${ nome }"
                    this.combustivel1 = nome
                } else {
                    val consumo: Double = it.data?.getDoubleExtra("consumo_combustivel_2", 0.0)!!.toDouble()
                    val nome: String = it.data?.getStringExtra("nome_combustivel_2")!!.toString()
                    edtConsumoCombustivel2.setText(consumo.toString())
                    txtLabelConsumoCombustivel2.text = "Consumo do combustível ${ nome }"
                    txtLabelPrecoCombustivel2.text = "Preço do combustível ${ nome }"
                    this.combustivel2 = nome
                }

            }

        }

    }

    private fun buscarConsumoCombustivel(orderCombustivel: Int) {
        val intent: Intent = Intent(applicationContext, CombustiveisActivity::class.java)

        if (orderCombustivel == 1) {
            intent.putExtra("buscar_consumo", 1)
        } else {
            intent.putExtra("buscar_consumo", 2)
        }

        getResultadoConsumoCombustivel.launch(intent)
    }

    override fun onClick(p0: View?) {

        if (p0!!.id == R.id.btn_limpar) {
            this.limpar()
        } else if (p0!!.id == R.id.btn_calcular) {
            this.calcularCombustivelMaisRentavel()
        } else if (p0!!.id == R.id.btn_consultar_consumo_combustivel_1) {
            this.buscarConsumoCombustivel(1)
        } else {
            this.buscarConsumoCombustivel(2)
        }

    }

}