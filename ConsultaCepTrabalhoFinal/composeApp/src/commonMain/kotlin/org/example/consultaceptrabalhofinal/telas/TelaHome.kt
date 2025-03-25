package org.example.consultaceptrabalhofinal.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.consultaceptrabalhofinal.models.Endereco
import org.example.consultaceptrabalhofinal.service.EnderecoService
import org.example.consultaceptrabalhofinal.validadores.aplicarMascaraCep
import org.example.consultaceptrabalhofinal.validadores.validarCep

@Composable
fun TelaHome(
    modifier: Modifier = Modifier
) {

    // meus states vão ficar aqui
    val scaffoldState = rememberScaffoldState()
    val cep: MutableState<String> = remember { mutableStateOf("") }
    val cepFormatado: MutableState<String> = remember { mutableStateOf("") }
    val consultando: MutableState<Boolean> = remember { mutableStateOf(false) }
    val erroConsultarEnderecoPeloCep: MutableState<String> = remember { mutableStateOf("") }
    val habilitarCampoCep: MutableState<Boolean> = remember { mutableStateOf(true) }
    val habilitarBotao: MutableState<Boolean> = remember { mutableStateOf(false) }
    val endereco: MutableState<Endereco> = remember { mutableStateOf( Endereco(
        cep = "",
        uf = "",
        logradouro = "",
        localidade = "",
        bairro = ""
    ) ) }
    val enderecoServico = EnderecoService()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize() // ocupar a tela inteira
    ) {

        Column(
            modifier = modifier.fillMaxSize()
                .padding(16.dp)
        ) {

            // campo para o usuário digitar o cep
            CampoCep(
                modifier = modifier,
                carregando = consultando.value,
                erroCampoCep = erroConsultarEnderecoPeloCep.value,
                cep = cepFormatado.value,
                habilitado = habilitarCampoCep.value,
                onDigitarCep = { cepDigitado ->
                    cep.value = cepDigitado.trim()

                    cepFormatado.value = aplicarMascaraCep(cep.value)

                    if (cepFormatado.value.length == 0) {
                        habilitarBotao.value = false
                        erroConsultarEnderecoPeloCep.value = "Informe o cep!"
                    } else if (validarCep(cepFormatado.value.trim()).isNotBlank()) {
                        habilitarBotao.value = false
                        erroConsultarEnderecoPeloCep.value = "Cep inválido!"
                    } else if (cepFormatado.value.length == 9) {
                        habilitarBotao.value = true
                        erroConsultarEnderecoPeloCep.value = ""
                    }

                }
            )

            // botão para consultar o endereço pelo cep
            BotaoConsultarCep(
                modifier = modifier,
                habilitado = habilitarBotao.value,
                consultando = consultando.value,
                onConsultar = {
                    // consultar endereço pelo cep
                    val cepDigitado = cepFormatado.value.trim()

                    println("Cep digitado: " + cepDigitado)

                    consultando.value = true
                    habilitarBotao.value = false
                    habilitarCampoCep.value = false
                    endereco.value = Endereco()

                    coroutineScope.launch {

                        try {
                            val resp = enderecoServico.consultarEnderecoPeloCep(cepFormatado.value.trim())

                            consultando.value = false
                            habilitarBotao.value = true
                            habilitarCampoCep.value = true

                            if (resp.cep.isNotBlank()) {

                                endereco.value = Endereco(
                                    cep = resp.cep,
                                    logradouro = resp.logradouro,
                                    bairro = resp.bairro,
                                    localidade = resp.localidade,
                                    uf = resp.uf
                                )

                            } else {
                                // não encontrou o endereço
                                println("Não encontrou o endereço para o cep ${ cepFormatado.value }")

                                // apresentar alerta de erro
                                scaffoldState.snackbarHostState.showSnackbar("Não foi encontrado um endereço para o cep em questão!")
                            }

                        } catch (e: Exception) {
                            println("Erro consultar endereço pelo cep: ${ e.message }")

                            consultando.value = false
                            habilitarBotao.value = true
                            habilitarCampoCep.value = true
                            endereco.value = Endereco()

                            // apresentar alerta de erro para o usuário
                            scaffoldState.snackbarHostState.showSnackbar("Erro ao tentar-se consultar o endereço pelo cep, aguarde alguns instantes e tente novamente!")
                        }

                    }

                }
            )

            // cep
            DadoEndereco(
                modifier = modifier,
                dado = endereco.value.cep,
                tituloDado = "CEP"
            )

            // logradouro
            DadoEndereco(
                modifier = modifier,
                dado = endereco.value.logradouro,
                tituloDado = "Logradouro"
            )

            // Bairro
            DadoEndereco(
                modifier = modifier,
                dado = endereco.value.bairro,
                tituloDado = "Bairro"
            )

            // Cidade
            DadoEndereco(
                modifier = modifier,
                dado = endereco.value.localidade,
                tituloDado = "Cidade"
            )

            // Estado
            DadoEndereco(
                modifier = modifier,
                dado = endereco.value.uf,
                tituloDado = "Estado"
            )

        }

    }

}

@Composable
fun CampoCep(
    modifier: Modifier,
    habilitado: Boolean = true,
    carregando: Boolean = false,
    cep: String = "",
    onDigitarCep: (String) -> Unit,
    erroCampoCep: String = ""
) {

    // campo de cep
    OutlinedTextField(
        value = cep,
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onValueChange = onDigitarCep,
        label = { Text("Digite o CEP") },
        enabled = habilitado,
        isError = erroCampoCep.isNotBlank(),
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number )
    )

    // caso tenha algum erro no campo de cep
    if (erroCampoCep.isNotBlank()) {
        Text(
            text = erroCampoCep.toString(),
            modifier = modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.subtitle2,
            color = Color.Red
        )
    }

}

@Composable
fun BotaoConsultarCep(
    modifier: Modifier,
    habilitado: Boolean = true,
    consultando: Boolean = false,
    onConsultar: () -> Unit
) {

    Button(
        modifier = modifier.fillMaxWidth()
            .padding(16.dp),
        content = {

                  if (!consultando) {
                    Text("Consultar")
                  } else {
                      // apresentar indicador de progresso
                      CircularProgressIndicator(
                          modifier = modifier,
                          color = Color.White
                      )
                  }

        },
        onClick = onConsultar,
        enabled = habilitado
    )

}

@Composable
fun DadoEndereco(
    modifier: Modifier,
    dado: String = "",
    tituloDado: String = ""
) {

    Row(
        modifier = modifier.fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "${ tituloDado.uppercase() } : ",
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = dado.trim(),
            style = MaterialTheme.typography.subtitle2
        )

    }

}