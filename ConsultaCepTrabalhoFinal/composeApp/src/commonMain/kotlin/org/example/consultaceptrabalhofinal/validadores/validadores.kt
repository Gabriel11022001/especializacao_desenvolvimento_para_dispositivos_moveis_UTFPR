package org.example.consultaceptrabalhofinal.validadores

fun validarCep(cep: String): String {

    if (cep.trim().isEmpty()) {

        return "Informe o cep!"
    }

    return ""
}

fun aplicarMascaraCep(cep: String): String {
    // remove todos os caracteres que não são dígitos
    val apenasDigitos = cep.replace(Regex("\\D"), "")

    // aplica a máscara
    return when {
        apenasDigitos.length <= 5 -> apenasDigitos
        apenasDigitos.length <= 8 -> "${apenasDigitos.substring(0, 5)}-${apenasDigitos.substring(5)}"
        else -> apenasDigitos.substring(0, 8) // limita a 8 dígitos
    }
}