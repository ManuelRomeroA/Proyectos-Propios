package com.example.cuentapalbrasiguales

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuentapalbrasiguales.databinding.ActivityMainBinding

/*
 * Reto #7
 * CONTANDO PALABRAS
 * Fecha publicación enunciado: 14/02/22
 * Fecha publicación resolución: 21/02/22
 * Dificultad: MEDIA
 *
 * Enunciado: Crea un programa que cuente cuantas veces se repite cada palabra y que muestre el recuento final de todas ellas.
 * - Los signos de puntuación no forman parte de la palabra.
 * - Una palabra es la misma aunque aparezca en mayúsculas y minúsculas.
 * - No se pueden utilizar funciones propias del lenguaje que lo resuelvan automáticamente.
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
        binding.btnIniciar.setOnClickListener { start() }
    }

    fun start(){
        /*Se inician las variables necesaria, Es importante agregarle a la oracion leida en el et el " " para
        que pueda leer la ultima palabra*/
        val oracion = binding.etOracion.text.toString() + " "
        val listWords: List<String>
        val listCount: List<Int>
        val finalList: List<String>
        var wordTview = ""

        /*Si el et esta vacio muestra error y no continua*/
        if (binding.etOracion.text.isBlank()) {
            showError()
        } else {
            //Separamos las palabras con countWords
            listWords = countWords(oracion)
            //Contamos cuantas palanbras iguales hay con compare Words
            listCount = compareWords(listWords)
            //Unimos las dos lista en una sola y eliminamos valores duplicados con listPlus
            finalList = listPlus(listWords,listCount)
            //String final con \n para separar cada item de finalList
            wordTview = finalWord(finalList.toMutableList())
        }
        binding.tvAnswer.text = wordTview
    }

    private fun showError() {
        Toast.makeText(this, "Debe Ingresar una Oracion", Toast.LENGTH_SHORT).show()
    }

    fun countWords(oracion: String): MutableList<String> {
        var word = ""
        val listWords: MutableList<String> = mutableListOf()

        for (char in oracion.indices) {
            //Depues de recorrer se agrega las condiciones y decoramos el String
            when {
                oracion[char].isUpperCase() -> word += oracion[char].lowercase()
                oracion[char].isLetterOrDigit() -> word += oracion[char]
                oracion[char].isWhitespace() -> {
                    word = "Cantidad de '' $word'' = "
                    listWords.add(word)
                    //Se debe resetear el string para que a la siguiente vuelta no guarde la posicion anterior
                    word = ""
                }
            }
        }
        return listWords
    }

    fun compareWords(listWords: MutableList<String>): MutableList<Int> {
        var cont = 0
        val listCont: MutableList<Int> = mutableListOf()
        //Recorremos uno detras del otro para saber si hay palabras iguales comparando i con word
        for (i in listWords) {
            for (word in listWords) {
                //si en
                if (word == i) {
                    cont++
                }
            }
            listCont.add(cont)
            cont = 0
        }
        return listCont
    }

    fun finalWord(list:MutableList<String>): String {
        var word = ""
        for(i in list){
            //Sumamos cada item de la lista en un String para que se vea mejor a la hora de imprimir
            word += "$i \n"
        }
        return word
    }

    fun listPlus(
        listWords: List<String>, listCompare: List<Int>): List<String> {
        val list: List<String> =
            //.zip ayuda a sumar ambas lista
            listWords.zip(listCompare) { listWords, listCompare -> listWords + listCompare.toString() }
        //toSet elimina valores duplicados
        val listFinal = list.toSet().toList()
        return listFinal
    }
}