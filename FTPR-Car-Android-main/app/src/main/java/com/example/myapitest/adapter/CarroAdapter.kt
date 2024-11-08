package com.example.myapitest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapitest.R
import com.example.myapitest.model.Carro
import com.example.myapitest.view_holder.CarroViewHolder
import com.squareup.picasso.Picasso
import java.util.ArrayList

class CarroAdapter(private val contexto: Context, private val onVisualizarCarro: (String) -> Unit): Adapter<CarroViewHolder>() {

    var carros: ArrayList<Carro> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val view: View = LayoutInflater.from(this.contexto).inflate(R.layout.item_car_layout, parent, false)

        return CarroViewHolder(view)
    }

    override fun getItemCount(): Int {

        return this.carros.size
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        // definir valores dos elementos do adapter
        val carro: Carro = this.carros[ position ]

        holder.txtNomeCarro.text = carro.nomeCarro.uppercase()
        holder.txtAnoCarro.text = carro.ano
        holder.txtLicencaCarro.text = carro.licenca

        val picasso = Picasso.get()

        picasso.load(carro.urlFotoCarro)
            .placeholder(R.drawable.ic_baixando_foto)
            .error(R.drawable.ic_erro_baixar_foto)
            .into(holder.imgCarro)

        // visualizar dados carro
        holder.itemView.setOnClickListener {
            this.onVisualizarCarro(carro.id)
        }
    }

}