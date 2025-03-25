package org.example.project.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LoaderCarregando(modifier: Modifier, texto: String = "") {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        CircularProgressIndicator(
            modifier = modifier.size(60.dp),
            color = Color.Black
        )

        Text(
            text = if (texto.isEmpty()) "Carregando, aguarde..." else texto,
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )

    }

}