package com.example.palindromo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.palindromo.databinding.ActivityMainBinding
import java.text.Normalizer

/*
 * Reto #12
 * ¿ES UN PALÍNDROMO?
 * Fecha publicación enunciado: 21/03/22
 * Fecha publicación resolución: 28/03/22
 * Dificultad: MEDIA
 *
 * Enunciado: Escribe una función que reciba un texto y retorne verdadero o falso (Boolean) según sean o no palíndromos.
 * Un Palíndromo es una palabra o expresión que es igual si se lee de izquierda a derecha que de derecha a izquierda.
 * NO se tienen en cuenta los espacios, signos de puntuación y tildes.
 * Ejemplo: Ana lleva al oso la avellana.
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
        binding.btnStart.setOnClickListener { start() }
    }

    private fun start() {

        val oracion = binding.etEntrada.text.toString()
        if (oracion.isBlank()) {
            showError()
        } else {
            val respuesta = binding.tvSalida
            val flag = isAPalidromo(oracion)

            if (flag) {
                respuesta.text = "Es Un Palidromo"
                respuesta.setTextColor(Color.GREEN)
            } else {
                respuesta.text = "No Es Un Palidromo"
                respuesta.setTextColor(Color.RED)
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "El Campo de texto no debe estar Vacio", Toast.LENGTH_SHORT).show()
        binding.etEntrada.text.clear()
        binding.tvSalida.text = ""

    }

    private fun isAPalidromo(oracion: String): Boolean {
        /* Se normaliza la oracion evitando las mayusculas y los acentos bajo la Forma NFD
         * El "[^\\p{ASCII} permite leer una cadena y remplazar en base a esa norma todos
         * los acentos y modificaciones que puede tener un string */
        val oracionNormalizer = Normalizer.normalize(oracion.lowercase(), Normalizer.Form.NFD)
            .replace("[^\\p{ASCII}]".toRegex(), "")
            .replace("[^a-z0-9]".toRegex(), "")
        return oracionNormalizer == oracionNormalizer.reversed()
    }
}