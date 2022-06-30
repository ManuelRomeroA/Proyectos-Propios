package com.example.eliminando_caracteres

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eliminando_caracteres.databinding.ActivityMainBinding

/*
 * Reto #11
 * ELIMINANDO CARACTERES
 * Fecha publicación enunciado: 14/03/22
 * Fecha publicación resolución: 21/03/22
 * Dificultad: FÁCIL
 *
 * Enunciado: Crea una función que reciba dos cadenas como parámetro (str1, str2) e imprima otras dos cadenas como salida (out1, out2).
 * - out1 contendrá todos los caracteres presentes en la str1 pero NO estén presentes en str2.
 * - out2 contendrá todos los caracteres presentes en la str2 pero NO estén presentes en str1.
 *
 * Información adicional:
 * - Usa el canal de nuestro discord (https://mouredev.com/discord) "🔁reto-semanal" para preguntas, dudas o prestar ayuda a la comunidad.
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
        binding.btnStart.setOnClickListener{ start() }
    }

    fun start() {
        val str1 = binding.etStr1.text.toString().trim()
        val str2 = binding.etStr2.text.toString().trim()
        if (str1.isBlank() || str2.isBlank()) {
            showError()
        } else {
            val strOut1 = readNotComun(str1, str2)
            val strOut2 = readNotComun(str2, str1)

            binding.tvOut1.text = strOut1
            binding.tvOut2.text = strOut2
        }
    }

    private fun showError() {
        Toast.makeText(this, "No debe haber ningun Campo de Texto Vacio", Toast.LENGTH_SHORT).show()
    }

    private fun readNotComun(str1: String, str2: String): String {
        var strOut = ""
        str1.lowercase().forEach {
            if (!str2.lowercase().contains(it)) {
                strOut += it
            }
        }
        return strOut
    }
}