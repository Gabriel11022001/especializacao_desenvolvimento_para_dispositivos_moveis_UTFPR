package com.example.myapitest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.example.myapitest.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(), OnClickListener {

    private lateinit var loginActivityBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var codigo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.loginActivityBinding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(this.loginActivityBinding.root)

        // configurar login do firebase com telefone
        this.configurarLoginFibebaseTelefone()

        // mapear eventos
        this.loginActivityBinding.btnLoginTelefone.setOnClickListener(this)
        this.loginActivityBinding.btnAutenticarSms.setOnClickListener(this)
    }

    private fun configurarLoginFibebaseTelefone() {
        this.auth = FirebaseAuth.getInstance()
    }

    // enviar o código de verificação para poder logar com telefone
    private fun loginComTelefone() {

        try {
            val telefone: String = this.loginActivityBinding.edtTelefoneLogin.text.toString()

            if (telefone.isBlank()) {
                this.apresentarAlerta(false, "Informe o número de telefone!")
            } else {
                val opcoes = PhoneAuthOptions.newBuilder(this.auth)
                    .setPhoneNumber(telefone)
                    .setTimeout(60, TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        override fun onVerificationCompleted(credencial: PhoneAuthCredential) {
                            // logar com a credencial
                            auth.signInWithCredential(credencial)
                                .addOnCompleteListener { resposta ->
                                    Log.d("concluiu_requisicao", resposta.isSuccessful.toString())

                                    if (resposta.isSuccessful) {
                                        apresentarAlerta(true, "Sms enviado com sucesso!")
                                    } else {
                                        apresentarAlerta(false, "Erro ao tentar-se logar!")
                                    }

                                }
                        }

                        override fun onVerificationFailed(p0: FirebaseException) {
                            // erro ao tentar-se enviar o código por sms
                            apresentarAlerta(false, "Erro ao tentar-se enviar o código SMS: ${ p0.message.toString() }")
                        }

                        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                            codigo = p0
                            apresentarAlerta(true, "Código enviado com sucesso por sms!")

                            // apresentar campo e botão para validar o código
                            loginActivityBinding.btnAutenticarSms.visibility = VISIBLE
                            loginActivityBinding.edtCodigoSms.visibility = VISIBLE
                        }

                    })
                    .build()

                PhoneAuthProvider.verifyPhoneNumber(opcoes)
            }

        } catch (e: Exception) {
            this.apresentarAlerta(false, "Erro: ${ e.message }")
        }

    }

    private fun autenticarComCodigoEnviadoSms() {

        try {
            val codigoDigitado: String = this.loginActivityBinding.edtCodigoSms.text.toString()

            if (codigoDigitado.isBlank()) {
                apresentarAlerta(false, "Informe o código de verificação!")
            } else {
                val credencial = PhoneAuthProvider.getCredential(this.codigo, codigoDigitado)
                auth.signInWithCredential(credencial)
                    .addOnCompleteListener(this) { resposta ->

                        if (resposta.isSuccessful) {
                            apresentarAlerta(true, "Código válido! Login efetuado com sucesso!")
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            apresentarAlerta(false, "Código inválido!")
                        }

                    }
            }

        } catch (e: Exception) {
            apresentarAlerta(false, "Erro ao tentar-se enviar o código de verificação para o firebase!")
        }

    }

    private fun apresentarAlerta(sucesso: Boolean = false, mensagem: String) {
        val snackBarAlerta: Snackbar = Snackbar.make(findViewById(android.R.id.content), mensagem, Snackbar.LENGTH_LONG)

        // definir a cor de fundo
        if (sucesso) {
            snackBarAlerta.setBackgroundTint(getColor(android.R.color.holo_green_dark))
        } else {
            snackBarAlerta.setBackgroundTint(getColor(android.R.color.holo_red_dark))
        }

        // apresentar a snackbar
        snackBarAlerta.show()
    }

    override fun onClick(p0: View?) {

        if (p0!!.id == R.id.btn_login_telefone) {
            this.loginComTelefone()
        }

        if (p0!!.id == R.id.btn_autenticar_sms) {
            this.autenticarComCodigoEnviadoSms()
        }

    }

}