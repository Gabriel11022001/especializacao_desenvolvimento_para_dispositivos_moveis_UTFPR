package org.example.consultaceptrabalhofinal.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.consultaceptrabalhofinal.models.Endereco

class EnderecoService {

    private val clienteHttp = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // consultar endereço pelo cep no servidor
    suspend fun consultarEnderecoPeloCep(cep: String): Endereco {

        return clienteHttp.get("https://viacep.com.br/ws/${ cep }/json").body()
    }

}