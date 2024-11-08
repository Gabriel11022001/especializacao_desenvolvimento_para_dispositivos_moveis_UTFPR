package com.example.myapitest.model

import com.google.gson.annotations.SerializedName

data class Posicao(
    @SerializedName("lat")
    var latitude: Double = 0.0,
    @SerializedName("long")
    var longitude: Double = 0.0
)