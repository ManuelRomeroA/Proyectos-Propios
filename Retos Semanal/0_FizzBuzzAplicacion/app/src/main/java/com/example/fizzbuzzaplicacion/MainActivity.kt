package com.example.fizzbuzzaplicacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fizzbuzzaplicacion.databinding.ActivityMainBinding


/*Este proyecto debe llevar las siguientes caracteristicas:
* -Imprimir en un Text View los numeros del 1 al 100
* -Si el numero es multimo de 3 debe escribir Fizz
* -Si el numero es multiplo de 5 debe escribir Buzz
* -Si el numero es multiplo de 3 y 5 debe escribir Fizz Buzz*/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener{startJustNum()}
    }


    private fun startJustNum(list: MutableList<String> = mutableListOf()) {
        for (num in 1..100) {
              when {
                  //el when pasara en ordenn, por eso es importante colocar la condicion doble al principio
                  num % 5 == 0 && num % 3 == 0 -> list.add("FizzBuzz")
                  num % 5 == 0 -> list.add("Buzz")
                  num % 3 == 0 -> list.add("Fizz")
                  else -> list.add(num.toString())
            }
            binding.tvJustNum.text = list.toString()
        }
    }
}
