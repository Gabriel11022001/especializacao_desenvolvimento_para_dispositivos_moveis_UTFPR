package org.example.project.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.project.componentes.LoaderCarregando

@Composable
fun TelaPessoaFormulario(modifier: Modifier = Modifier) {

    val carregando: Boolean = false
    val erroCarregar: Boolean = false

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBarFormPessoa(
            modifier,
            novaPessoa = true,
            onRetornar = {
                // retornar para a tela de listagem de pessoas
            }
        ) }
    ) { innerPadding ->

        if (carregando) {
            LoaderCarregando(modifier, texto = "Carregando pessoa, aguarde...")
        } else if (erroCarregar) {
            ErroCarregarPessoaForm()
        } else {
            FormularioPessoa()
        }

    }

}

@Composable
fun TopAppBarFormPessoa(
    modifier: Modifier = Modifier,
    novaPessoa: Boolean = false,
    onRetornar: () -> Unit
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        Button(onRetornar) {
            Text("Retornar")
        }
        Text(if (novaPessoa) "Cadastrar pessoa" else "Editar pessoa")
    }

}