package com.example.app_consumo_retrofit

import android.app.Application
import android.util.Log

class AppConsumoRetrofitApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        this.init()
    }

    private fun init() {
        /**
         * quando o app for startado, vai executar esse application que eu criei,
         * então, aqui é ideal criar pro exemplo, uma base de dados local
         */
        Log.d("teste_application", "Executando o application que eu criei!")
    }

}