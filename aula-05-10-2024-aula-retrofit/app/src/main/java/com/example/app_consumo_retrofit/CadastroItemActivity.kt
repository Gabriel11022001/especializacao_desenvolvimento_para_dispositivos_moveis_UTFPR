package com.example.app_consumo_retrofit

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Secure
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_consumo_retrofit.databinding.ActivityCadastroItemBinding
import com.example.app_consumo_retrofit.service.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.SecureRandom
import kotlin.random.Random

class CadastroItemActivity : AppCompatActivity() {

    private lateinit var cadastroItemBinding: ActivityCadastroItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.cadastroItemBinding = ActivityCadastroItemBinding.inflate(layoutInflater)

        setContentView(this.cadastroItemBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.cadastroItemBinding.btnSalvar.setOnClickListener { this.salvar() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.retornar()

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        this.retornar()
    }

    private fun retornar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun validarForm(): Boolean {

        if (this.cadastroItemBinding.edtNome.text.toString().isBlank()) {
            Toast.makeText(this, "Informe o nome!", Toast.LENGTH_SHORT).show()

            return false
        }

        if (this.cadastroItemBinding.edtSobrenome.text.toString().isBlank()) {
            Toast.makeText(this, "Informe o sobrenome!", Toast.LENGTH_SHORT).show()

            return false
        }

        if (this.cadastroItemBinding.edtEndereco.text.toString().isBlank()) {
            Toast.makeText(this, "Informe o endereço!", Toast.LENGTH_SHORT).show()

            return false
        }

        if (this.cadastroItemBinding.edtIdade.text.toString().isBlank()) {
            Toast.makeText(this, "Informe a idade!", Toast.LENGTH_SHORT).show()

            return false
        }

        if (this.cadastroItemBinding.edtIdade.text.toString().toInt() <= 0) {
            Toast.makeText(this, "Idade inválida!", Toast.LENGTH_SHORT).show()

            return false
        }

        if (this.cadastroItemBinding.edtUrlImagem.text.toString().isBlank()) {
            Toast.makeText(this, "Informe a imagem!", Toast.LENGTH_SHORT).show()

            return false
        }

        return true
    }

    private fun salvar() {

        try {

            if (this.validarForm()) {

                // enviar o cliente para o servidor com corrotina
                val idRandomico = SecureRandom().nextInt().toString()

                val valueCadastrar = Value()

                valueCadastrar.id = idRandomico

                valueCadastrar.nome = this.cadastroItemBinding.edtNome.text.toString().trim()
                valueCadastrar.sobrenome = this.cadastroItemBinding.edtSobrenome.text.toString().trim()
                valueCadastrar.idade = this.cadastroItemBinding.edtIdade.text.toString().toInt()
                valueCadastrar.endereco = this.cadastroItemBinding.edtEndereco.text.toString().trim()
                valueCadastrar.imagemUrl = this.cadastroItemBinding.edtUrlImagem.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val retrofitClient = RetrofitClient()
                    val apiService = retrofitClient.apiService

                    apiService.cadastrar(valueCadastrar)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG)
                            .show()

                        Log.d("nome", valueCadastrar.nome)
                        Log.d("sobrenome", valueCadastrar.sobrenome)
                        Log.d("endereco", valueCadastrar.endereco)
                        Log.d("idade", valueCadastrar.idade.toString())
                        Log.d("imagem", valueCadastrar.imagemUrl)

                        retornar()
                    }

                }

            }

        } catch (e: Exception) {
            Toast.makeText(this, "Erro: ${ e.message.toString() }", Toast.LENGTH_SHORT).show()
        }

    }

}