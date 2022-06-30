package com.example.numerobinario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.numerobinario.databinding.ActivityMainBinding

/*
 * Reto #8
 * DECIMAL A BINARIO
 * Fecha publicación enunciado: 18/02/22
 * Fecha publicación resolución: 02/03/22
 * Dificultad: FÁCIL
 *
 * Enunciado: Crea un programa se encargue de transformar un número decimal a binario sin utilizar funciones propias del lenguaje que lo hagan directamente.
 *
 * Información adicional:
 * - Usa el canal de nuestro discord (https://mouredev.com/discord) "🔁reto-semanal" para preguntas, dudas o prestar ayuda a la acomunidad.
 * - Puedes hacer un Fork del repo y una Pull Request al repo original para que veamos tu solución aportada.
 * - Revisaré el ejercicio en directo desde Twitch el lunes siguiente al de su publicación.
 * - Subiré una posible solución al ejercicio el lunes siguiente al de su publicación.
 *
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener { numberDecimal() }


    }

    fun numberDecimal(): String {
        val inNumber = binding.etNumber.text.toString()
        var binaryNumber = ""
        var number = inNumber.toInt() * 2
        var rest: Int

        while (number > 1) {
            number /= 2
            rest = number % 2
            binaryNumber += "$rest"
        }
        when (binaryNumber.length) {
            1 -> binaryNumber += "000"
            2 -> binaryNumber += "00"
            3 -> binaryNumber += "0"
        }
        binding.tvAnswer.text = binaryNumber.reversed()
        return binaryNumber
    }
}