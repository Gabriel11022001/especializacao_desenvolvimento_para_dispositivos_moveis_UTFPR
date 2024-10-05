package com.example.app_consumo_retrofit

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("id")
    var id: String = ""
    @SerializedName("value")
    var valor: Value? = null

}