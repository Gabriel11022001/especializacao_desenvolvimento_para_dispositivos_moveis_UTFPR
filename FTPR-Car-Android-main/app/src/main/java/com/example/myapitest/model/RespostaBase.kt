package com.example.myapitest.model

import com.google.gson.annotations.SerializedName

data class RespostaBase(
    @SerializedName("message")
    var mensagem: String = ""
)