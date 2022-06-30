package com.example.expresionesequilibradas

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expresionesequilibradas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { start() }
    }

    @SuppressLint("SetTextI18n")
    fun start() {
        val expression = binding.etEntrada.text.toString()
        if (expression.isBlank()) {
            showError()
        } else {
            val flag: Boolean = isBalanced(expression)
            if (flag) {
                binding.tvSalida.text = "La expression esta Balanceada"
                binding.tvSalida.setTextColor(Color.GREEN)
            } else {
                binding.tvSalida.text = "La expresion No esta Balanceada"
                binding.tvSalida.setTextColor(Color.RED)
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "El campo de Texto no debe estar Vacio", Toast.LENGTH_SHORT).show()
    }

    private fun isBalanced(expression: String): Boolean {

        val symbols = mapOf("{" to "}", "[" to "]", "(" to ")")
        val stack = arrayListOf<String>()

        expression.forEach {

            val symbol = it.toString()
            val containsKey = symbols.containsKey(symbol)

            if (containsKey || symbols.containsValue(symbol)) {
                if (containsKey) {
                    stack.add(symbol)
                } else if (stack.isEmpty() || symbol != symbols[stack.removeLast()]) {
                    return false
                }
            }
        }
        return stack.isEmpty()
    }
}