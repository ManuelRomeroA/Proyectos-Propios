package com.example.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fibonacci.databinding.ActivityMainBinding

/*Reto #2
* LA SUCESIÓN DE FIBONACCI
* Fecha publicación enunciado: 10/01/22
* Fecha publicación resolución: 17/01/22
* Dificultad: DIFÍCIL
*
* Enunciado: Escribe un programa que imprima los 50 primeros números de la sucesión de Fibonacci empezando en 0.
* La serie Fibonacci se compone por una sucesión de números en la que el siguiente siempre es la suma de los dos anteriores.
* 0, 1, 1, 2, 3, 5, 8, 13...
*
* Información adicional:
* - Usa el canal de nuestro discord (https://mouredev.com/discord) "🔁reto-semanal" para preguntas, dudas o prestar ayuda a la acomunidad.
* - Puedes hacer un Fork del repo y una Pull Request al repo original para que veamos tu solución aportada.
* - Revisaré el ejercicio en directo desde Twitch el lunes siguiente al de su publicación.
* - Subiré una posible solución al ejercicio el lunes siguiente al de su publicación.
*/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIniciar.setOnClickListener{iniciar()}
    }

    fun iniciar() {

        val listFibo: MutableList<Long> = mutableListOf()
        var firstNum: Long = 0
        var secondNum: Long = 1
        var result: Long
        listFibo.add(firstNum)
        listFibo.add(secondNum)

        for (i in 1..48) {
            result = firstNum + secondNum
            listFibo.add(result)
            binding.tvFibo.text = listFibo.toString()
            firstNum = secondNum
            secondNum = result

        }
    }

}