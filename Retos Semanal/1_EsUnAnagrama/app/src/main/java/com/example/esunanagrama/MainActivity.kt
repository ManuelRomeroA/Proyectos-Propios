package com.example.esunanagrama

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.Toast
import com.example.esunanagrama.databinding.ActivityMainBinding

//Reto #1
/* ¿ES UN ANAGRAMA?
* Fecha publicación enunciado: 03/01/22
* Fecha publicación resolución: 10/01/22
* Dificultad: MEDIA
*
* Enunciado: Escribe una función que reciba dos palabras (String) y retorne verdadero o falso (Boolean) según sean o no anagramas.
* Un Anagrama consiste en formar una palabra reordenando TODAS las letras de otra palabra inicial.
* NO hace falta comprobar que ambas palabras existan.
* Dos palabras exactamente iguales no son anagrama.
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
        binding.btnIniciar.setOnClickListener { checkAnagram() }


    }

    private fun showError() {
        if (binding.etFirstWord.text.isEmpty() || binding.etSecondWord.text.isEmpty()) {
            binding.etFirstWord.text.clear()
            binding.etSecondWord.text.clear()
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }


    private fun checkAnagram() {

        showError()
        val firstWord = binding.etFirstWord.text.toString()
        val secondWord = binding.etSecondWord.text.toString()
        val listPrueba1: MutableList<String> = mutableListOf()
        val listPrueba2: MutableList<String> = mutableListOf()


        //Ordenamos las dos palanras por orden Alfabetico
        sortWord(firstWord, listPrueba1)
        binding.tvPruebaPalabra1.text = listPrueba1.toString()
        sortWord(secondWord, listPrueba2)
        binding.tvPruebaPalabra2.text = listPrueba2.toString()

        //Si las palabras ordenadas no son iguales -> No es un Anagrama
        if (listPrueba1.toString() != listPrueba2.toString()) {
            binding.tvAnswer.text = "Esto es no un Anagrama"
            binding.tvAnswer.setTextColor(Color.YELLOW)
        } else if (listPrueba1.isEmpty() || listPrueba2.isEmpty()) {
            binding.tvAnswer.text = "Error"
            binding.tvAnswer.setTextColor(Color.RED)
        } else {
            binding.tvAnswer.text = "Esto es un Anagrama"
            binding.tvAnswer.setTextColor(Color.GREEN)
        }
    }

    private fun sortWord(word: String, list: MutableList<String>) {
        for (a in word) {
            list.add(a.toString())
            list.sort()
        }
    }

    private fun condition(word1: String) {
        if (word1.isNotBlank()) {
            showError()

        }
    }
}