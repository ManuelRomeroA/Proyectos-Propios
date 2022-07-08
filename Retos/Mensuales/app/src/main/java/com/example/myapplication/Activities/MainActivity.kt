package com.example.myapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

/*El diseño es de libre elección.
 *La temática de las preguntas del cuestionario será de libre elección. Puede estar bien seguir un mismo tema (por ejemplo, preguntas sobre el universo "Harry Potter").
 *En la pantalla inicial podrás comenzar a jugar o consultar el ranking de puntuaciones.
 *El juego consistirá en 10 preguntas aleatorias con 3 respuestas y sólo una correcta. Recomendable crear más de 10 preguntas para que no siempre salgan las mismas. Cada vez que se responde a una pregunta, se pasará a la siguiente.
 *Disponemos de 30 segundos para responder cada pregunta. El contador deberá aparecer en la pantalla, y si llega a 0 se tomará como respuesta incorrecta y se pasará a la siguiente pregunta.
 *Al marcar una respuesta o finalizar el tiempo, se mostrará si se ha acertado o no la pregunta, dando feedback sobre cuál sería la respuesta correcta y navegando al cabo de un par de segundos a la siguiente pantalla.
 *Sistema de puntuación:
 *Contador a 0 o respuesta incorrecta = 0 puntos.
 *Contador mayor 0 y respuesta correcta = [segundos restantes] puntos. Ej: Si quedaban 9 segundos para efinalizar la cunta atrás y se acierta la pregunta, se asignan 9 puntos.
 *Una vez finalizada la pregunta número 10 se mostrará en una nueva pantalla la puntuación final y se deberá introducir un nombre para guardarla de forma persistente (aunque cerremos la app). Hecho estos se mostrará la pantalla de ranking.
 *La pantalla de ranking muestra ordenados de mayor a menor los 10 mejores resultados y el nombre guardado. Desde esta pantalla siempre se podrá navegar a la pantalla inicial.*/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIniciar.setOnClickListener { start() }


    }

    private fun start() {
        if (binding.etName.text.isBlank()) {
            showError()
        } else {
            goToQuiz()
        }
    }

    private fun goToQuiz() {
        val nameUser = binding.etName.text.toString()
        nameUser.trim().uppercase()
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("NAME", nameUser)
        startActivity(intent)
    }

    private fun showError() {
        Toast.makeText(this, "Debe ingresar su nombre para Iniciar", Toast.LENGTH_SHORT).show()
    }

}