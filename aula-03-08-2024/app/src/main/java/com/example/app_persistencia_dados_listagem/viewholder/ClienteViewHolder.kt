package com.example.app_persistencia_dados_listagem.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.app_persistencia_dados_listagem.R

class ClienteViewHolder(view: View): ViewHolder(view) {

    var txtNomeCliente: TextView
    var txtEmailCliente: TextView

    init {
        this.txtNomeCliente = view.findViewById(R.id.txt_nome)
        this.txtEmailCliente = view.findViewById(R.id.txt_email)
    }

}