package com.example.inviertestring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.inviertestring.databinding.ActivityMainBinding
import kotlin.Array as arra
import kotlin.collections.ArrayList as ArrayList1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIniciar.setOnClickListener{voltearWord()}
    }

    private fun voltearWord() {
        if(binding.etWord.text.isEmpty()){
            showError()
        }else{
            binding.tvResult.text = readWord()
        }
    }

    private fun readWord(): String {
        val word = binding.etWord.text
        var newWord = ""

        for(char in (word.length-1)  downTo 0){
            newWord += word[char]
        }
        return newWord
    }

    private fun showError() {
        Toast.makeText(this,"Debes Ingresar una Oracion/Palabra",Toast.LENGTH_SHORT).show()
    }
}