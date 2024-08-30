package com.example.app_contas_a_pagar

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_contas_a_pagar.adapter.IOnDeletarLancamento
import com.example.app_contas_a_pagar.adapter.IOnPagarLancamento
import com.example.app_contas_a_pagar.adapter.LancamentoAdapter
import com.example.app_contas_a_pagar.dao.LancamentoDAO
import com.google.android.material.snackbar.Snackbar

class LancamentosActivity : AppCompatActivity() {

    private lateinit var lancamentoDAO: LancamentoDAO
    private lateinit var lancamentoAdapter: LancamentoAdapter
    private lateinit var recyclerLancamentos: RecyclerView
    private lateinit var contexto: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lancamentos)

        this.contexto = this

        this.lancamentoDAO = LancamentoDAO(this)

        // mapear elementos de interface
        this.mapearElementosInterface()

        // preparar listeners do adapter
        val iOnDeletarLancamento: IOnDeletarLancamento = object : IOnDeletarLancamento {

            override fun deletar(idLancamento: Int) {
                // configurar deleção de lançamentos
                val builderDeletarLancamento: AlertDialog.Builder = AlertDialog.Builder(contexto)
                builderDeletarLancamento.setCancelable(false)
                val viewAlertaDeletar = layoutInflater.inflate(R.layout.alerta, null, false)
                val txtTituloAlertaDeletar: TextView = viewAlertaDeletar.findViewById(R.id.txt_titulo_alerta)
                val txtMensagemDeletar: TextView = viewAlertaDeletar.findViewById(R.id.txt_mensagem)
                val btnFechar: Button = viewAlertaDeletar.findViewById(R.id.btn_fechar)
                val btnConfirmar: Button = viewAlertaDeletar.findViewById(R.id.btn_confirmar)
                txtTituloAlertaDeletar.text = "Deletar lançamento"
                txtMensagemDeletar.text = "Deseja mesmo deletar o lançamento?"
                builderDeletarLancamento.setView(viewAlertaDeletar)

                val dialog = builderDeletarLancamento.create()

                dialog.show()

                btnFechar.setOnClickListener {
                    dialog.dismiss()
                }

                btnConfirmar.setOnClickListener {
                    // confirmar deleção do lançamento
                    lancamentoDAO.deletar(idLancamento)
                    dialog.dismiss()
                    Toast.makeText(contexto, "Deleção realizada com sucesso!", Toast.LENGTH_LONG)
                        .show()
                    // listar novamente todos os lançamentos cadastrados
                    listarLancamentos()
                }

            }

        }

        val iOnPagarLancamento: IOnPagarLancamento = object : IOnPagarLancamento {

            override fun pagar(idLancamento: Int) {
                // configurar o pagamento de lançamentos
                lancamentoDAO.alterarStatusPago(idLancamento)
                Toast.makeText(contexto, "Pagamento realizado com sucesso!", Toast.LENGTH_LONG)
                    .show()
                listarLancamentos()
            }

        }

        // configurar adapter
        this.lancamentoAdapter = LancamentoAdapter(this, iOnDeletarLancamento, iOnPagarLancamento)

        // configurar a RecyclerView
        this.recyclerLancamentos.layoutManager = LinearLayoutManager(this)
        this.recyclerLancamentos.adapter = this.lancamentoAdapter
    }

    private fun mapearElementosInterface() {
        this.recyclerLancamentos = findViewById(R.id.recycler_lancamentos)
    }

    override fun onStart() {
        super.onStart()
        this.listarLancamentos()
    }

    private fun listarLancamentos() {
        // listar os lançamentos

        try {
            val lancamentos = this.lancamentoDAO.buscarTodos()
            this.lancamentoAdapter.setLancamentos(lancamentos)
            this.lancamentoAdapter.notifyDataSetChanged()

            if (lancamentos.size == 0) {
                Snackbar.make(findViewById(android.R.id.content), "Não existem lançamentos cadastrados no banco de dados!", Snackbar.LENGTH_LONG)
                    .show()
            }

        } catch (e: Exception) {
            Log.e("erro_consultar_lancamentos", "Ocorreu um erro: ${ e.message.toString() }")
            Snackbar.make(findViewById(android.R.id.content), "Erro! Retorne a tela anterior e retorne para a tela atual!", Snackbar.LENGTH_LONG)
                .show()
        }

    }

}