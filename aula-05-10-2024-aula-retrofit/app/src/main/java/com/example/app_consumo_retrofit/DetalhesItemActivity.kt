package com.example.app_consumo_retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.app_consumo_retrofit.databinding.ActivityDetalhesItemBinding
import com.example.app_consumo_retrofit.service.RetrofitClient
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalhesItemActivity : AppCompatActivity() {

    private lateinit var detalhesItemBinding: ActivityDetalhesItemBinding
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.detalhesItemBinding = ActivityDetalhesItemBinding.inflate(layoutInflater)

        setContentView(this.detalhesItemBinding.root)

        this.setUpView()

        val id: String = intent.getStringExtra("id")!!

        if (id != null) {

            if (id.isNotBlank()) {
                this.buscarItem(id)
            }

        }

    }

    private fun setUpView() {
        // apresentar o botão de voltar na ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.detalhesItemBinding.btnDeletarItem.setOnClickListener { this.deletar() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.retornar()

        return super.onOptionsItemSelected(item)
    }

    private fun retornar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        this.retornar()
    }

    private fun buscarItem(idItem: String) {

        try {

            CoroutineScope(Dispatchers.IO).launch {
                val retrofitClient = RetrofitClient()
                val apiService = retrofitClient.apiService

                val resp = apiService.buscarPeloId(idItem)

                withContext(Dispatchers.Main) {

                    if (resp != null) {
                        Log.d("sucesso", "Encontrou o item com o id informado!")
                        item = resp
                        apresentarDetalhesItem()
                    } else {
                        Log.e("erro", "Não foi encontrado um item com esse id!")
                        detalhesItemBinding.txtErro.text = "Ocorreu um erro ao tentar-se buscar o item pelo id!"
                    }

                }
            }

        } catch (e: Exception) {
            Log.e("erro", e.message.toString())
        }

    }

    private fun apresentarDetalhesItem() {
        val it = this.item!!

        Log.d("id", it.valor!!.id)
        this.detalhesItemBinding.txtNomeItem.text = it.valor!!.nome + " " + it.valor!!.sobrenome
        this.detalhesItemBinding.txtIdadeItem.text = it.valor!!.idade.toString() + " anos de idade"
        this.detalhesItemBinding.txtEnderecoItem.text = it.valor!!.endereco
        this.detalhesItemBinding.txtErro.visibility = View.GONE

        // apresentar a imagm utilizando o picaso
        val picaso = Picasso.get()
            .load(it.valor!!.imagemUrl)
            .placeholder(R.drawable.ic_baixando)
            .error(R.drawable.ic_erro)
            .into(this.detalhesItemBinding.imgItem)
    }

    private fun deletar() {

        try {

            CoroutineScope(Dispatchers.IO).launch {
                val id = item!!.id
                val retrofitClient = RetrofitClient()
                val apiService = retrofitClient.apiService

                apiService.deletar(id)

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Deletado com sucesso!", Toast.LENGTH_LONG).show()
                    retornar()
                }

            }

        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao tentar-se deletar!", Toast.LENGTH_LONG)
                .show()
        }

    }

}