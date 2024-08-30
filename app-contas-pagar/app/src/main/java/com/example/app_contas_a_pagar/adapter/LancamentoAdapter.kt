package com.example.app_contas_a_pagar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.app_contas_a_pagar.R
import com.example.app_contas_a_pagar.model.Lancamento
import com.example.app_contas_a_pagar.viewholder.LancamentoViewHolder
import java.util.ArrayList

class LancamentoAdapter(
    val contexto: Context,
    val iOnDeletarLancamento: IOnDeletarLancamento,
    val iOnPagarLancamento: IOnPagarLancamento
): Adapter<LancamentoViewHolder>() {

    private var lancamentos: ArrayList<Lancamento> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LancamentoViewHolder {
        val layoutInflater = LayoutInflater.from(this.contexto)
        val view = layoutInflater.inflate(R.layout.lancamento_adapter, parent, false)

        return LancamentoViewHolder(view)
    }

    override fun getItemCount(): Int {

        return this.lancamentos.size
    }

    override fun onBindViewHolder(holder: LancamentoViewHolder, position: Int) {
        val lancamento = this.lancamentos[ position ]
        holder.txtValor.text = "R${ lancamento.valor }"
        holder.txtDataLancamento.text = lancamento.dataLancamento

        if (lancamento.pago) {
            // lançamento pago
            holder.txtStatus.text = "Lançamento pago"
            holder.txtStatus.setTextColor(contexto.getColor(android.R.color.holo_green_dark))
            holder.checkBoxPagar.visibility = View.GONE
        } else {
            holder.txtStatus.text = "Aguardando pagamento"
            holder.txtStatus.setTextColor(contexto.getColor(android.R.color.holo_blue_dark))
            holder.checkBoxPagar.visibility = View.VISIBLE
        }

        // mapear evento de deleção do lançamento
        holder.btnDelatar.setOnClickListener {
            iOnDeletarLancamento.deletar(lancamento.id)
        }

        // mapear evento de pagar o lançamento
        holder.checkBoxPagar.setOnCheckedChangeListener { checkBox, i ->
            iOnPagarLancamento.pagar(lancamento.id)
        }

    }

    // método para alterar os lançamentos no adapter e notificar o mesmo
    fun setLancamentos(lancamentos: ArrayList<Lancamento>) {
        this.lancamentos.clear()
        this.lancamentos.addAll(lancamentos)
    }

}