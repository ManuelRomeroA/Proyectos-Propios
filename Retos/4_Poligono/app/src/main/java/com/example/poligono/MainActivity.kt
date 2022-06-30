package com.example.poligono

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.poligono.databinding.ActivityMainBinding
import com.example.poligono.tipos.Cuadrado

/*
 * Reto #4
 * ÁREA DE UN POLÍGONO
 * Fecha publicación enunciado: 24/01/22
 * Fecha publicación resolución: 31/01/22
 * Dificultad: FÁCIL
 *
 * Enunciado: Crea UNA ÚNICA FUNCIÓN (importante que sólo sea una) que sea capaz de calcular y retornar el área de un polígono.
 * - La función recibirá por parámetro sólo UN polígono a la vez.
 * - Los polígonos soportados serán Triángulo, Cuadrado y Rectángulo.
 * - Imprime el cálculo del área de un polígono de cada tipo.
 *
 * Información adicional:
 * - Usa el canal de nuestro discord (https://mouredev.com/discord) "🔁reto-semanal" para preguntas, dudas o prestar ayuda a la acomunidad.
 * - Puedes hacer un Fork del repo y una Pull Request al repo original para que veamos tu solución aportada.
 * - Revisaré el ejercicio en directo desde Twitch el lunes siguiente al de su publicación.
 * - Subiré una posible solución al ejercicio el lunes siguiente al de su publicación.
 *
 */

class MainActivity : AppCompatActivity() {
    val listPoligono = arrayOf("Cuadrado","Triangulo","Rectangulo","Circulo")

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListPoligono()

        binding.btnIniciar.setOnClickListener{goToPoligono()}
    }
    //Encargado de mandar a la activity si el usuario selecciono previamente un poligono
    private fun goToPoligono() {
        val namePoligono = binding.tvPoligono.text
        if(namePoligono.isEmpty()){
            showError()
        }else{
            sendInformation()
        }
    }
    //Muestra un error
    private fun showError(){
        Toast.makeText(this,"Debe ingresar un poligono",Toast.LENGTH_SHORT).show()
    }
    //Evio de informacion a DetailsActivity
    private fun sendInformation(){
        val namePoligono = binding.tvPoligono.text
        val intent = Intent(this,DeatailsPoligono::class.java)
        intent.putExtra("NAMEPOLIGONO",namePoligono)
        startActivity(intent)
    }
    //Funcion que crea una Alerta para mostrar el Array de Seleccion Poligono
    private fun setListPoligono(){
        binding.tvPoligono.setOnClickListener{
            val setpoligono = AlertDialog.Builder(this)
            setpoligono.setItems(listPoligono){_,pos ->
                val poligono = listPoligono[pos]
                binding.tvPoligono.text = poligono
            }
            val alert =setpoligono.create()
            alert.setTitle("Elija un poligono")
            alert.show()
        }
    }
}