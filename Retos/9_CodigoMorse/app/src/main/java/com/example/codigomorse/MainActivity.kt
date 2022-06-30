package com.example.codigomorse

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.codigomorse.databinding.ActivityMainBinding

/*
 * Reto #9
 * CÓDIGO MORSE
 * Fecha publicación enunciado: 02/03/22
 * Fecha publicación resolución: 07/03/22
 * Dificultad: MEDIA
 *
 * Enunciado: Crea un programa que sea capaz de transformar texto natural a código morse y viceversa.
 * - Debe detectar automáticamente de qué tipo se trata y realizar la conversión.
 * - En morse se soporta raya "—", punto ".", un espacio " " entre letras o símbolos y dos espacios entre palabras "  ".
 * - El alfabeto morse soportado será el mostrado en https://es.wikipedia.org/wiki/Código_morse.
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

        /*Lo primero que va hacer la app es cargar la view con los dependiendo del estado del switch*/
        stringOrMorse()
        /*Al presionar el switch cambia la lectura de datos (si quiere morse o texto)*/
        binding.swCambio.setOnClickListener { stringOrMorse() }
        binding.btnStart.setOnClickListener { stringOrMorse() }
    }

    //Funcion para pasar un texto a Codigo Morse
    fun readWord() {
        val oracion = binding.etEntrada.text
        val findCd = Morse()
        var word = ""

        //Confirma si el Edit Text no esta vacio o solo con espacios
        if (binding.etEntrada.text.isBlank()) {
            showError()
        } else {
            //Comiensa el a leer el Edit Text y lo imprime en la tvSalida
            for (char in oracion) {
                when {
                    char.isLowerCase() -> word += findCd.mapa[char.uppercaseChar()] + " "
                    char.isUpperCase() -> word += findCd.mapa[char] + " "
                    char.isWhitespace() -> word += "  "
                }
            }
            binding.tvSalida.text = word
        }
    }
    //Funcion para pasar de Codigo morse a Texto
    fun readMorse() {
        val oracion = binding.etEntrada.text.toString()
        val listOut: MutableList<String> = mutableListOf()
        /*con "Split" separa el codigo por palabras (recordando que un codigo morse sus palabras
         *separan con doble espacio*/
        val listIn: MutableList<String> = oracion.split("  ") as MutableList<String>
        var word = ""
        //En caso de tener alguna letra o digito llama al error 2
        showError2(oracion)
        //En caso de que este vacio o en blanco llama a error
        if (oracion.isBlank()) {
            showError()
        } else {
            //Inicia la lectura de la oracion para agregar cada palabra en un lista
            for (i in listIn.indices) {
                listOut.add(listIn[i])
            }
            /*Llama a la funcion countMorse y ademas se asegura de que no exista espacios blancos
             *en el inicio y evitar doble espacio en blanco (solo se necesita un espacio) a*/
            for (j in listOut.indices) {
                word += countMorse(listOut[j].trim()) + " "
            }
            binding.tvSalida.text = word
        }
    }
    //Funcion que cuenta cada palabra del codigo
    fun countMorse(oracion: String): String {
        val readOracion = "$oracion "
        //Es mas comodo para el usuario escribir '-' pero se ve aprecia mejor con '—'
        val oracionFinal = readOracion.replace('-', '—')
        val findCd = Morse()
        //Se invierte el mapa
        val invertedFindCd = findCd.mapa.entries.associateBy({ it.value }, { it.key })
        var word = ""
        var finalWord = ""
        val listWord: MutableList<String> = mutableListOf()
        //Cada letra de una palabra se separa con un espacio, se lee la palabra y se guarda en una lista
        for (i in oracionFinal.indices) {
            word += oracionFinal[i]
            if (oracionFinal[i].isWhitespace()) {
                listWord.add(word.trim())
                word = ""
            }
        }
        /*se lee cada item que tiene la lista anterior y se convierte en codigo morse para al final unir
         *cada letra en una palabra*/
        for ((_, item) in listWord.withIndex()) {
            finalWord += invertedFindCd[item]
        }
        return finalWord
    }
    //Funcion que un mensaje de error
    fun showError() {
        Toast.makeText(this, "Debe ingresar un texto o Codigo morse", Toast.LENGTH_SHORT).show()
    }
    //Funcion que muestra si el Edit text tiene letra o digito para asi mostrar el error
    fun showError2(oracion: String) {
        for (char in oracion)
            if (char.isLetterOrDigit()) {
                Toast.makeText(
                    this,
                    "El codigo morse no debe tener letras o numeros",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etEntrada.text.clear()
            }
    }
    //Funcion que decide como va a trabajar la app (si en morse/texto o texto/morse) en base al switch
    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    fun stringOrMorse() {
        val entrada = binding.etEntrada
        val title = binding.tvTitle
        val switch = binding.swCambio
        if (switch.isChecked) {
            title.text = "Morse a Texto"
            entrada.hint = "Ingrese Morse"
            entrada.text.clear()
            binding.btnStart.setOnClickListener {
                readMorse()
            }
        } else {
            title.text = "Texto a Morse"
            entrada.hint = "Ingrese Oracion"
            entrada.text.clear()
            binding.btnStart.setOnClickListener {
                readWord()
            }
        }
    }
}