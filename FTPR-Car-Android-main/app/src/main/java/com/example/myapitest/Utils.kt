package com.example.myapitest

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utils {

    companion object {

        fun encryptStringWithTimestamp(input: String): String {
            // Obtém a data atual em formato timestamp
            val currentTimestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            // Concatena a string de entrada com a data atual
            val concatenatedString = "$input$currentTimestamp"

            // Criptografa a string concatenada usando SHA-256
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(concatenatedString.toByteArray())

            // Converte o hash em uma string hexadecimal
            return hashBytes.joinToString("") { "%02x".format(it) }
        }

    }

}