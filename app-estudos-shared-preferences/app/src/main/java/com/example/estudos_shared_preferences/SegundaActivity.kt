package com.example.estudos_shared_preferences

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SegundaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        // obtendo as preferências compartilhadas
        val preferenciasCompartilhadas = getSharedPreferences("preferencias_app", MODE_PRIVATE)
        Log.d("versao", preferenciasCompartilhadas.getString("versao", "Não trouxe versão")!!)
        Log.d("usuario_bloqueado", "" + if (preferenciasCompartilhadas.getBoolean("usuario_bloqueado", false)) "Sim" else "Não")
        Log.d("nome_app", preferenciasCompartilhadas.getString("nome_app", "")!!)
    }

}