package com.example.app_consumo_retrofit

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_consumo_retrofit.adapter.ItemAdapter
import com.example.app_consumo_retrofit.databinding.ActivityMainBinding
import com.example.app_consumo_retrofit.service.RetrofitClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // utilizar o viewBinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter

    // objeto que serve para solicitar permissão do usuário
    private lateinit var locationPermissionLaucher: ActivityResultLauncher<String>
    // objeto para manipular localização do usuário
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * com o viewBinding eu não preciso ficar mapeando os elementos
         * de interface utilizando o método findViewById, eu posso simplesmente
         * acessar os elementos de interface por meio do objeto que representa o
         * viewbinding
         */
        this.binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(this.binding.root)

        // solicitar permissão do usuário para acessar a localização do mesmo
        this.solicitarPermissaoLocalizacaoUsuario()

        // configurar a view
        this.setUpView()
    }

    override fun onResume() {
        super.onResume()
        this.listarItens()
    }

    private fun visualizar(item: Item) {
        val intentDetalhesItem = Intent(this, DetalhesItemActivity::class.java)

        intentDetalhesItem.putExtra("id", item.valor!!.id)
        startActivity(intentDetalhesItem)
        finish()
    }

    private fun setUpView() {
        this.binding.recyclerItens.layoutManager = LinearLayoutManager(this)
        // configurar adapter

        // listener de quando o usuário clicar no item da lista, que será passado para o adapter
        val itemClickListener: (Item) -> Unit = {
            Log.d("id", it.id)
            visualizar(item = it)
        }

        this.itemAdapter = ItemAdapter(this, itemClickListener)
        this.binding.recyclerItens.adapter = this.itemAdapter

        this.binding.btnAdicionarNovoItem.setOnClickListener {
            // redirecionar usuário para a tela de cadastro de item
            startActivity(Intent(this, CadastroItemActivity::class.java))
            finish()
        }

        // evento de realizar o refresh
        this.binding.swRefreshItens.setOnRefreshListener {
            this.binding.swRefreshItens.isRefreshing = true
            this.listarItens()
        }
    }

    private fun listarItens() {

        // fazer uma requisição utilizando corrotina
        CoroutineScope(Dispatchers.IO).launch {
            val retrofitClient = RetrofitClient()
            val apiService = retrofitClient.apiService

            var msg: String = ""
            var resp: List<Item>? = null

            try {
                resp = apiService.listarTodos()
                msg = "Sucesso"
            } catch (e: Exception) {
                msg = e.message.toString()
            }

            // voltando para a thread principal
            withContext(Dispatchers.Main) {
                binding.swRefreshItens.isRefreshing = false

                if (msg == "Sucesso") {

                    if (resp != null) {
                        apresentarItens(resp)
                    }

                } else {
                    Log.d("erro", msg)
                }

            }
        }

    }

    private fun apresentarItens(itens: List<Item>) {

        val itensArrayList = ArrayList<Item>()

        itens.forEach {
            Log.d("id", it.valor!!.id)
            Log.d("nome", it.valor!!.nome)
            Log.d("img", it.valor!!.imagemUrl)
            itensArrayList.add( it )
        }

        this.itemAdapter.setItens( itensArrayList )
    }

    private fun solicitarPermissaoLocalizacaoUsuario() {

        try {
            // inicializar o nosso fusedLocation
            this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            // configurar o lancher para solicitar permissão do usuário
            this.locationPermissionLaucher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { deuPermissao ->

                if (deuPermissao) {
                    Toast.makeText(this, "Permissão concedida!", Toast.LENGTH_SHORT).show()
                    this.getUltimaLocalizacao()
                } else {
                    Toast.makeText(this, "Permissão não concedida, você não poderá utilizar o serviço de localização!", Toast.LENGTH_LONG)
                        .show()
                }

            }

            this.verificarSolicitacaoPermissao()
        } catch(e: Exception) {
            Log.e("erro_localizacao", e.message.toString())
        }

    }

    private fun verificarSolicitacaoPermissao() {

        when {
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                this.getUltimaLocalizacao()
            }

            shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION) -> {
                this.locationPermissionLaucher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }

            shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                this.locationPermissionLaucher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            }

            else -> {
                this.locationPermissionLaucher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

    }

    // obter a ultima localização do usuário
    private fun getUltimaLocalizacao() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // o usuário não deu permissão, solicitar novamente
            Log.d("permissao_loca_nao_concedida", "O usuário não informou a permissão, solicitar novamente!")
            this.solicitarPermissaoLocalizacaoUsuario()
        } else {
            Log.d("permissao_localizacao", "Permissão de localização permitida!")

            this.fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->

                Log.d("localizacao_sucesso", task.isSuccessful.toString())

                if (task.isSuccessful) {
                    val localizacao = task.result

                    if (localizacao == null) {
                        Toast.makeText(this, "Não obteve a localização!", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        // obteve a localização
                        Toast.makeText(this, "Obteve a localização corretamente!", Toast.LENGTH_LONG)
                            .show()
                        Log.d("localizacao", localizacao.latitude.toString())
                    }

                } else {
                    Toast.makeText(applicationContext, "Erro na licalização!", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

    }

}