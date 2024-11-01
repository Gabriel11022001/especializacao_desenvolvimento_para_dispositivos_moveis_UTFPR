package com.example.myapitest.model

import com.google.gson.annotations.SerializedName

data class CarroConsulta(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("value")
    var carro: Carro? = null
)