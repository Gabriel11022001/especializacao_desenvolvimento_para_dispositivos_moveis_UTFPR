package com.example.app_consumo_retrofit.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.app_consumo_retrofit.Item
import com.example.app_consumo_retrofit.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class ItemAdapter(
    private val contexto: Context,
    private val itemClickListener: (Item) -> Unit
): Adapter<ItemViewHolder>() {

    private var itens: ArrayList<Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(this.contexto)
        val view = inflater.inflate(R.layout.item_adapter, parent, false)

        return ItemViewHolder( view )
    }

    override fun getItemCount(): Int {

        return this.itens.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Item = this.itens[ position ]

        holder.nome.text = "${ item.valor!!.nome } ${ item.valor!!.sobrenome }"
        holder.endereco.text = item.valor!!.endereco
        holder.idade.text = "${ item.valor!!.idade } anos de idade"
        holder.profissao.text = item.valor!!.profession

        val picaso = Picasso.get()

        picaso.load(item.valor!!.imagemUrl)
            .placeholder(R.drawable.ic_baixando) // enquanto estiver baixando a imagem
            .error(R.drawable.ic_erro) // se der erro ao tentar-se baixar a imagem
            .into(holder.foto) // baixou com sucesso

        // evento quando clicar no item da lista
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(item)
        }
    }

    fun setItens(itens: ArrayList<Item>) {
        this.itens = itens
        notifyDataSetChanged()
    }

}

data class ItemViewHolder(
    val view: View,
    val foto: ImageView = view.findViewById(R.id.img_foto_item),
    val nome: TextView = view.findViewById(R.id.txt_nome_item),
    val idade: TextView = view.findViewById(R.id.txt_idade_item),
    val endereco: TextView = view.findViewById(R.id.txt_endereco_item),
    val profissao: TextView = view.findViewById(R.id.txt_profissao)
): ViewHolder(view)
