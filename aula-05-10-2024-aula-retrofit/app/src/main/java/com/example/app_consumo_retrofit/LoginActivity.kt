package com.example.app_consumo_retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_consumo_retrofit.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity(), OnClickListener {

    private lateinit var loginActivityBinding: ActivityLoginBinding
    private lateinit var gsoClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLoginLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.loginActivityBinding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(this.loginActivityBinding.root)

        // configurar login com google firebase
        this.setUpLoginGoogle()

        // mapear eventos
        this.loginActivityBinding.btnLoginGoogle.setOnClickListener(this)
        this.loginActivityBinding.btnLoginTelefone.setOnClickListener(this)
    }

    private fun setUpLoginGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("652460444492-7h2rbe41eekfjmon7rvdhseujtoke2il.apps.googleusercontent.com")
            .requestEmail()
            .build()

        this.gsoClient = GoogleSignIn.getClient(this, gso)

        this.firebaseAuth = FirebaseAuth.getInstance()

        this.googleLoginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

            try {
                val contaUsuarioFezLogin = task.result

                if (contaUsuarioFezLogin != null) {
                    this.loginFirebaseGoogle(contaUsuarioFezLogin.idToken!!)
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Erro login google: ${ e.message }", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    private fun loginFirebaseGoogle(token: String) {
        val credencial = GoogleAuthProvider.getCredential(token, null)
        this.firebaseAuth.signInWithCredential(credencial).addOnCompleteListener { resultado ->

            if (resultado.isSuccessful) {
                val usuarioLogado = this.firebaseAuth.currentUser
                Log.d("usuario_logado", usuarioLogado?.uid.toString())
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
                finish()
            } else {
                Toast.makeText(this, "Erro login google firebase!", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    // realizar login com conta do google
    private fun loginContaGoogle() {

        try {
            this.googleLoginLauncher.launch(this.gsoClient.signInIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Erro login google: ${ e.message }", Toast.LENGTH_LONG)
                .show()
        }

    }

    override fun onClick(p0: View?) {

        if (p0!!.id == R.id.btn_login_google) {
            // realizar login com conta do google
            this.loginContaGoogle()
        } else if (p0!!.id == R.id.btn_login_telefone) {
            // realizar login com telefone celular
        }

    }

}