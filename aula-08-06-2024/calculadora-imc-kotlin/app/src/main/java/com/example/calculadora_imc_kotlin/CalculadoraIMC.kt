package com.example.calculadora_imc_kotlin

class CalculadoraIMC {

    fun calcular(peso: Double, altura: Double): Double {

        return peso / (altura * altura)
    }

}