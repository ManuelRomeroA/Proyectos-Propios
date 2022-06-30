package com.example.harrypotterrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.harrypotterrecyclerview.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    val listHouse = arrayOf(
        "Gryffindor",
        "Slytherin",
        "Hufflepuff",
        "Revenclaw"
    )

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegresar.setOnClickListener(){getBack()}
        binding.txtAddHouse.setOnClickListener(){setHouse()}


    }

    private fun getBack(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
    private fun setHouse(){
        val house = AlertDialog.Builder(this)
        house.setItems((listHouse)){_,pos->
           val addHouse = listHouse[pos]
           binding.txtAddHouse.setText(addHouse)
        }
        val alertTitle = house.create()
        alertTitle.setTitle("Elige La Casa")
        alertTitle.show()
    }

}


