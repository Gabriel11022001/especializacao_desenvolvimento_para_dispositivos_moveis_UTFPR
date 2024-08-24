package com.example.estudos_shared_preferences

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * posso utilizar as preferências compartilhadas para passar dados de uma tela para
         * outra, slavar configurações do app ou salvar dados do usuário logado, etc...
         */

        // definindo as preferências compartilhadas
        val preferenciasCompartilhadas = getSharedPreferences("preferencias_app", MODE_PRIVATE)
        val editorPreferenciasCompartilhadas = preferenciasCompartilhadas.edit()
        editorPreferenciasCompartilhadas.putString("versao", "v1.0")
        editorPreferenciasCompartilhadas.putString("nome_app", "Aplicativo de teste")
        editorPreferenciasCompartilhadas.putBoolean("usuario_bloqueado", true)

        // comitando as alterações
        editorPreferenciasCompartilhadas.commit()

        val btnRedirecionar: Button = findViewById(R.id.btn_redirecionar)
        btnRedirecionar.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }
    }

}