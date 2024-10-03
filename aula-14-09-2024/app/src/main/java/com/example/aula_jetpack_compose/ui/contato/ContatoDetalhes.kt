package com.example.aula_jetpack_compose.ui.contato

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aula_jetpack_compose.data.Contato
import com.example.aula_jetpack_compose.ui.theme.AulajetpackcomposeTheme

@Composable
fun ContatoDetalhes(modifier: Modifier = Modifier) {

    val contato: MutableState<Contato> = rememberSaveable{ mutableStateOf(Contato()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        }
    ) { paddingValues ->

        val paddingPadrao = modifier.padding(paddingValues)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuApp(modifier: Modifier = Modifier) {

    TopAppBar(
        title = { Text(text = "Detalhes do contato") },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {

            IconButton(onClick = {}) {

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Retornar..."
                )

            }

        }
    )

}

@Composable
@Preview(showBackground = true)
private fun MenuAppPreview(modifier: Modifier = Modifier) {
    
    AulajetpackcomposeTheme {
        
        MenuApp()
        
    }
    
}