package com.example.aula_componentes_interface

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener, OnCheckedChangeListener {

    private lateinit var btnTestarComponentes: AppCompatButton
    private lateinit var rdgSexos: RadioGroup
    private lateinit var rbSexoMasculino: RadioButton
    private lateinit var rbSexoFeminino: RadioButton
    private lateinit var dtDataNascimento: DatePicker
    private lateinit var autoCompleteCidade: AutoCompleteTextView
    private lateinit var spnProfissao: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mapear elementos de interface
        this.btnTestarComponentes = findViewById(R.id.btn_testar_componentes)
        this.rdgSexos = findViewById(R.id.radio_group)
        this.rbSexoFeminino = findViewById(R.id.rb_sexo_feminino)
        this.rbSexoMasculino = findViewById(R.id.rb_sexo_masculino)
        this.dtDataNascimento = findViewById(R.id.dt_data_nascimento)
        this.autoCompleteCidade = findViewById(R.id.auto_complete_cidade)
        this.spnProfissao = findViewById(R.id.spn_profissao)

        // configurando o adapter para o AutoCompleteTextView
        val cidades: ArrayList<String> = arrayListOf("Bastos", "Tupã", "Iacri")
        val autoCompleteCidadesAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, cidades)
        this.autoCompleteCidade.setAdapter(autoCompleteCidadesAdapter)

        // configurando o adapter do Spinner
        val profissoes: List<String> = listOf("Desenvolvedor", "Analista de QA", "Analista de suporte")
        val adapterProfissaoes: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, profissoes)
        this.spnProfissao.adapter = adapterProfissaoes

        // mapear eventos
        this.btnTestarComponentes.setOnClickListener(this)
        this.rdgSexos.setOnCheckedChangeListener(this)
    }

    override fun onClick(p0: View?) {
        // testar o sexo selecionado
        val sexoSelecionado: String = if (this.rdgSexos.checkedRadioButtonId == R.id.rb_sexo_masculino) "Masculino" else "Feminino"
        Log.d("sexo_selecionado", sexoSelecionado)

        // testar data selecionada
        val dia: Int = this.dtDataNascimento.dayOfMonth
        val mes: Int = this.dtDataNascimento.month + 1
        val ano: Int = this.dtDataNascimento.year
        val data: String = "${ dia }/${ mes }/${ ano }"
        Log.d("data", data)

        // testar profissão selecionada
        val profissao: String = this.spnProfissao.selectedItem.toString()
        Log.d("profissao", profissao)
    }

    /**
     * evento invocado quando o usuário seleciona um RadioButton no RadioGroup
     */
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

        if (p1 == R.id.rb_sexo_masculino) {
            // Toast.makeText(this, "O usuário selecionou o sexo Masculino!", Toast.LENGTH_SHORT).show()
            Snackbar.make(findViewById(android.R.id.content), "O usuário selecionou o sexo Masculino!", Snackbar.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "O usuário selecionou o sexo Feminino!", Toast.LENGTH_SHORT).show()
            Snackbar.make(findViewById(android.R.id.content), "O usuário selecionou o sexo Feminino!", Snackbar.LENGTH_SHORT).show()
        }

    }

}