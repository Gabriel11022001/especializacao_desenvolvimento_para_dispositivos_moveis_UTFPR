package com.example.aula_jetpack_compose.ui.contato

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula_jetpack_compose.R
import com.example.aula_jetpack_compose.data.Contato
import com.example.aula_jetpack_compose.ui.theme.AulajetpackcomposeTheme
import com.example.aula_jetpack_compose.ui.theme.Typography

@Composable
fun ListaContatos(modifier: Modifier = Modifier) {

    val carregando: Boolean = false
    val contatos: MutableState<ArrayList<Contato>> = remember { mutableStateOf( gerarContatosFake() ) }

    val marcarContatoComoPreferido: (Contato) -> Unit = { contato ->
        Log.d("teste", "Caiu na função para marcar o contato como favorito!")
        val contatosNovos: ArrayList<Contato> = ArrayList()

        for (contatoAtual in contatos.value) {
            Log.d("teste", "nome do contato: ${ contatoAtual.nome }")

            if (contatoAtual.favorito) {
                contatoAtual.favorito = false
            } else {
                contatoAtual.favorito = true
            }

            contatosNovos.add(contatoAtual)
        }

        contatos.value = contatosNovos

        for (contatoAtual in contatos.value) {
            Log.d("teste_favorito", contatoAtual.nome + " - " + " favorito: " + contatoAtual.favorito)
        }

    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MenuApp() },
        floatingActionButton = {
            
            ExtendedFloatingActionButton(modifier = modifier, onClick = {}) {
                
                Text(text = "Adicionar contato")
                
            }
            
        }
    ) { paddingValues ->

        val modifierPadrao = Modifier.padding(paddingValues)

        if (carregando) {

            Loader(modifier = modifierPadrao)

        } else if (contatos.value.size == 0) {

            ListaVazia(modifier = modifierPadrao)

        } else {

            Lista(contatos = contatos.value, modifier = modifierPadrao, marcarContatoPreferido = marcarContatoComoPreferido)

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuApp(modifier: Modifier = Modifier) {

    TopAppBar(
        title = { Text(text = "Contatos") },
        modifier = modifier.fillMaxWidth()
    )

}

@Composable
@Preview(showBackground = true)
private fun MenuAppPreview(modifier: Modifier = Modifier) {

    AulajetpackcomposeTheme {

        MenuApp()

    }

}

@Composable
private fun Loader(modifier: Modifier = Modifier) {

    // posicionar elementos um abaixo do outro
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Carregando os contatos, aguarde...",
            // modifier = modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Blue
            )
        )

    }

}

@Composable
@Preview(showBackground = true)
private fun LoderPreview(modifier: Modifier = Modifier) {

    Loader()

}

@Composable
fun ListaVazia(modifier: Modifier = Modifier) {

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.no_data),
            contentDescription = "Sem dados..."
        )

        Text(
            text = "Nada por aqui...",
            color = Color.Blue,
            style = Typography.bodyLarge,
            modifier = Modifier.padding(top = 12.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Você ainda não adicionou nenhum contato.",
            color = Color.Blue,
            style = Typography.bodyMedium,
            modifier = Modifier.padding(top = 5.dp),
            textAlign = TextAlign.Center
        )

    }

}

@Composable
@Preview(showBackground = true)
fun ListaVaziaPreview(modifier: Modifier = Modifier) {
 
    AulajetpackcomposeTheme {

        ListaVazia()

    }

}

@Composable
fun ContatoItem(modifier: Modifier = Modifier, contato: Contato, marcarContatoPreferido: (Contato) -> Unit) {

    ListItem(
        headlineContent = { Text(text = contato.nome) },
        trailingContent = {

            IconButton(onClick = { marcarContatoPreferido(contato) }) {

                Icon(
                    imageVector = if (contato.favorito)
                    {
                        Icons.Filled.Favorite
                    } else
                    {
                        Icons.Filled.FavoriteBorder
                    },
                    contentDescription = "Favoritar...",
                    tint = if (contato.favorito)
                    {
                        Color.Red
                    } else
                    {
                        LocalContentColor.current
                    }
                )

            }
        }
    )

}

@Composable
private fun Lista(modifier: Modifier = Modifier, contatos: ArrayList<Contato>, marcarContatoPreferido: (Contato) -> Unit) {

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        for (contato in contatos) {

            ContatoItem(
                contato = contato,
                marcarContatoPreferido = marcarContatoPreferido
            )

        }

    }

}

@Composable
@Preview(showBackground = true)
fun ListaPreview(modifier: Modifier = Modifier) {

    val contatosFake: ArrayList<Contato> = gerarContatosFake()

    Lista(contatos = contatosFake, marcarContatoPreferido = {})

}

private fun gerarContatosFake(): ArrayList<Contato> {
    val contatosFake: ArrayList<Contato> = arrayListOf()

    for (contador in 1..100) {
        val contato = Contato()
        contato.id = contador
        contato.nome = "Contato ${ contador }"
        contato.email = "contato${ contador }@gmail.com"
        contato.telefone = "(14) 998776554"
        contato.sobrenome = " Sem sobrenome ${ contador }"

        if (contador % 2 == 0) {
            contato.favorito = true
        } else {
            contato.favorito = false
        }

        contatosFake.add(contato)
    }

    return contatosFake
}