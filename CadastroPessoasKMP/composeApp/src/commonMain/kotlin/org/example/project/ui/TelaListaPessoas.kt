package org.example.project.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.Pessoa
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.componentes.LoaderCarregando

@Composable
fun TelaListaPessoas(
    modifier: Modifier = Modifier,
    viewModel: ListaPessoasViewModel = viewModel()
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ListaPessoasTopBar(modifier) {
            // fazer o reload
            viewModel::carregarPessoas
        } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // redirecionar o usuário para a tela de cadastro de pessoas
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { innerPadding ->

        if (viewModel.state.carregando) {
            // apresentar um loader
            LoaderCarregando(modifier = Modifier.padding(innerPadding))
        } else if (viewModel.state.ocorreuErro) {
            // apresentar tela com erro
            ErroCarregarPessoas(
                modifier = Modifier.padding(innerPadding),
                onTentarNovamente = {}
            )
        } else {
            // apresentra a listagem de pessoas
            ListaPessoas(
                modifier = Modifier.padding(innerPadding),
                pessoas = viewModel.state.pessoas,
                onSelecionarPessoa = { idPessoaSelecionada ->
                    println("Id da pessoa selecionada: ${ idPessoaSelecionada }")
                }
            )
        }

    }

}

@Composable
fun ListaPessoasTopBar(modifier: Modifier = Modifier, onReload: () -> Unit) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        Text("Pessoas")
        Button(onClick = onReload) {
            Text("Reload")
        }
    }

}

@Composable
fun ErroCarregarPessoas(modifier: Modifier, onTentarNovamente: () -> Unit) {

}

@Composable
fun ListaPessoas(
    modifier: Modifier,
    pessoas: List<Pessoa>,
    onSelecionarPessoa: (Int) -> Unit
) {

    if (pessoas.isEmpty()) {
        // apresentar componente lista vazia
    } else {
        // apresentar a listagem de pessoas
        LazyColumn(
            modifier = modifier.padding(top = 4.dp)
        ) {
            itemsIndexed(pessoas) { indice, pessoa ->
                ItemListaPessoas(
                    pessoa = pessoa,
                    onSelecionarPessoa = onSelecionarPessoa,
                    modifier = modifier
                )

                if (indice < pessoas.lastIndex) {
                    Divider(modifier = modifier.padding(vertical = 4.dp))
                }

            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemListaPessoas(
    modifier: Modifier,
    pessoa: Pessoa,
    onSelecionarPessoa: (Int) -> Unit
) {

    ListItem(
        modifier = modifier.padding(8.dp).clickable {
            // evento de clique
            onSelecionarPessoa(pessoa.id)
        }
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Text("Nome da pessoa: " + pessoa.nomeCompleto)
            Text("Telefone: " + pessoa.telefone)
            Text("E-mail: " + pessoa.email)
            Text("Cpf: " + pessoa.cpf)
        }
    }

}