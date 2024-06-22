package com.example.calculadora_gorjeta

class CalculadoraGorjeta {

    companion object {

        fun calcular(valorTotal: Double, percentualGorjeta: Double): Double {

            return (percentualGorjeta / 100.0) * valorTotal
        }

    }

}