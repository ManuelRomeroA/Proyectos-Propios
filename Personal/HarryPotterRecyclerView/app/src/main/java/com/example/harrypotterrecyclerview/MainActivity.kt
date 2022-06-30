package com.example.harrypotterrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotterrecyclerview.Adapter.magoAdapter
import com.example.harrypotterrecyclerview.databinding.ActivityMainBinding
import com.example.harrypotterrecyclerview.databinding.MagoBinding
import com.example.harrypotterrecyclerview.magoProvider.Companion.magoList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.btnAgregar.setOnClickListener{addMage()}
    }
    private fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = magoAdapter(magoProvider.magoList)
    }
    private fun addMage() {
       val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

}