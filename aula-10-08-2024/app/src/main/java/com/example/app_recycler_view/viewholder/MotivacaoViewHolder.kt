package com.example.app_recycler_view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.app_recycler_view.R

class MotivacaoViewHolder(view: View): ViewHolder(view) {

    var txtDescricao: TextView
    var imgMotivacao: ImageView

    init {
        this.txtDescricao = view.findViewById(R.id.txt_motivacao)
        this.imgMotivacao = view.findViewById(R.id.img_motivacao)
    }

}