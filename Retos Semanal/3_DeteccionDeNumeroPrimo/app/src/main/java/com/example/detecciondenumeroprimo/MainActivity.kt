package com.example.detecciondenumeroprimo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.detecciondenumeroprimo.databinding.ActivityMainBinding
import java.util.Locale.filter

/*
 * Reto #3
 * ¿ES UN NÚMERO PRIMO?
 * Fecha publicación enunciado: 17/01/22
 * Fecha publicación resolución: 24/01/22
 * Dificultad: MEDIA
 *
 * Enunciado: Escribe un programa que se encargue de comprobar si un número es o no primo.
 * Hecho esto, imprime los números primos entre 1 y 100.
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

        binding.btnIniciar.setOnClickListener {checkPrimo()}
    }

    private fun checkPrimo() {
        val listNumber: MutableList<String> = mutableListOf()
        val numberPrimo = binding.etNumber.text.toString()
        val nInicio = 1
        val nFinal = 100

        //Si el campo esta vacio Muestra Error y No continues
        if (binding.etNumber.text.toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar un Numero", Toast.LENGTH_SHORT).show()
        }else{
        /*La funcion solo Retornara los numeros primo que esten en el rago establecio
        * en este caso sera entre el 1 al 100 */
        for (i in nInicio..nFinal) {
            if (checkNumber(i)){
                listNumber.add(i.toString())
            }
        }
        //Imprimimos la lista de numeros en el tvListprimo
        binding.tvEnunciado.text = "Existen ${listNumber.size} numeros entre $nInicio y $nFinal son:"
        binding.tvListPrimo.text = listNumber.toString()
        checkNumber(numberPrimo.toInt())
        }
    }

    private fun checkNumber(number: Int):Boolean {
        /*Un numero primo cuando el resto es 0 cuando divides el numero entre 1 y el mismo
        Es por ello que en el ciclo for solo pasara despues del uno y llegara hasta el numero*/
        if (number < 2) {
            sayNoPrimo()
            return false
        }
        for (i in 2 until number){
            if (number % i == 0){
                sayNoPrimo()
                return false
            }
        }
        sayPrimo()
        return true
    }
// Definimos el tvAnswer como primo y no primo
    private fun sayNoPrimo() {
        binding.tvAnswer.text = "No Es Primo"
        binding.tvAnswer.setTextColor(Color.RED)
    }

    private fun sayPrimo(){
        binding.tvAnswer.text = "Es Primo"
        binding.tvAnswer.setTextColor(Color.GREEN)
    }

}

