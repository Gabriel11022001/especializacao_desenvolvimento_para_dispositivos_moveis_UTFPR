package com.example.app_consumo_retrofit

import com.google.gson.annotations.SerializedName

class Value {

    @SerializedName("id")
    var id: String = ""
    @SerializedName("name")
    var nome: String = ""
    @SerializedName("surname")
    var sobrenome: String = ""
    @SerializedName("address")
    var endereco: String = ""
    @SerializedName("imageUrl")
    var imagemUrl: String = ""
    @SerializedName("age")
    var idade: Int = 0
    @SerializedName("profession")
    var profession: String = ""
}