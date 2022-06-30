package com.example.myapplication.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInformation()

    }

    fun getInformation() {
        val bundle = intent.extras
        val name = bundle?.get("NAME").toString()
        val cont = bundle?.get("RESULTADO").toString()

        binding.tvName.text = name
        binding.tvResultado.text = "Haz Logrado una puntuacion de $cont/11"
        binding.btnMainActivity.setOnClickListener { sendRanking(name,cont) }
    }

    fun sendRanking(name:String ,cont:String?) {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("NAME", name)
        intent.putExtra("RESULTADO", cont)
        startActivity(intent)
    }
}