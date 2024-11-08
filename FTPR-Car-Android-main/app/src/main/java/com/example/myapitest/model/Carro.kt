package com.example.myapitest.model

import com.google.gson.annotations.SerializedName

data class Carro(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("imageUrl")
    var urlFotoCarro: String = "",
    @SerializedName("year")
    var ano: String = "",
    @SerializedName("name")
    var nomeCarro: String = "",
    @SerializedName("licence")
    var licenca: String = "",
    @SerializedName("place")
    var posicao: Posicao? = null,
    @SerializedName("error")
    var erro: String = ""
)