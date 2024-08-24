package com.example.app_persistencia_dados_listagem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.app_persistencia_dados_listagem.R
import com.example.app_persistencia_dados_listagem.model.Cliente
import com.example.app_persistencia_dados_listagem.viewholder.ClienteViewHolder

class ClienteAdapter(val contexto: Context): Adapter<ClienteViewHolder>() {

    private var clientes: ArrayList<Cliente> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val layoutInflater = LayoutInflater.from(this.contexto)
        val view = layoutInflater.inflate(R.layout.cliente_adapter, parent, false)

        return ClienteViewHolder(view)
    }

    override fun getItemCount(): Int {

        return this.clientes.size
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        holder.txtNomeCliente.text = this.clientes.get(position).nome
        holder.txtEmailCliente.text = this.clientes.get(position).email
    }

    fun setClientes(clientes: ArrayList<Cliente>) {
        this.clientes = clientes
        notifyDataSetChanged()
    }

}