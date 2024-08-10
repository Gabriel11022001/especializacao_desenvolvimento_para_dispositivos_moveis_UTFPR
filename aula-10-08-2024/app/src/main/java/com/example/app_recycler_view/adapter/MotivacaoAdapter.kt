package com.example.app_recycler_view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.app_recycler_view.R
import com.example.app_recycler_view.model.Motivacao
import com.example.app_recycler_view.viewholder.MotivacaoViewHolder
import java.util.ArrayList

class MotivacaoAdapter(val contexto: Context): Adapter<MotivacaoViewHolder>() {

    private var motivacoes: ArrayList<Motivacao> = ArrayList()

    /**
     * inflar a view que vai representar o elemento da lista
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotivacaoViewHolder {
        val layoutInflater = LayoutInflater.from(contexto)
        val view = layoutInflater.inflate(R.layout.motivacao_adapter, parent, false)

        return MotivacaoViewHolder(view)
    }

    // retorna a quantidade de elementos que serão renderizados na RecyclerView
    override fun getItemCount(): Int {

        return this.motivacoes.size
    }

    // configurar os elementos de interface da view definindo seus valores
    override fun onBindViewHolder(holder: MotivacaoViewHolder, position: Int) {
        val motivacao: Motivacao = this.motivacoes.get(position)
        holder.txtDescricao.text = motivacao.descricao

        // definir a imagem de cada motivação
        if (motivacao.id == 1) {
            holder.imgMotivacao.setImageResource(R.drawable.image1)
        } else if (motivacao.id == 2) {
            holder.imgMotivacao.setImageResource(R.drawable.image2)
        } else if (motivacao.id == 3) {
            holder.imgMotivacao.setImageResource(R.drawable.image3)
        } else if (motivacao.id == 4) {
            holder.imgMotivacao.setImageResource(R.drawable.image4)
        } else {
            holder.imgMotivacao.setImageResource(R.drawable.image5)
        }

    }

    fun setDados(motivacoes: ArrayList<Motivacao>) {
        this.motivacoes.addAll(motivacoes)
        notifyDataSetChanged()
    }

}