package com.example.app_contas_a_pagar.viewholder

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.app_contas_a_pagar.R

class LancamentoViewHolder(view: View): ViewHolder(view) {

    var txtValor: TextView = view.findViewById(R.id.txt_valor_lancamento)
    var txtStatus: TextView = view.findViewById(R.id.txt_status_lancamento)
    var txtDataLancamento: TextView = view.findViewById(R.id.txt_data_lancamento)
    var btnDelatar: ImageButton = view.findViewById(R.id.btn_deletar_lancamento)
    var checkBoxPagar: CheckBox = view.findViewById(R.id.check_pagar_lancamento)

}