package com.example.poligono

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import com.example.poligono.databinding.ActivityDeatailsPoligonoBinding
import com.example.poligono.tipos.Circulo
import com.example.poligono.tipos.Cuadrado
import com.example.poligono.tipos.Rectangulo
import com.example.poligono.tipos.Triangulo

class DeatailsPoligono : AppCompatActivity() {
    private lateinit var binding: ActivityDeatailsPoligonoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeatailsPoligonoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Llamo a getInformation para moldear mi DetailsActivity
        getInformation()
        binding.btnCalcular.setOnClickListener{area()}
        binding.btnAtras.setOnClickListener{getBack()}

    }
    //Funcion para calcular el Area de la figura
    private fun area() {
        val namePoligono = getInformation()
        val propiedad1 = binding.etPropiedad1.text.toString()
        val propiedad2 = binding.etPropiedad2.text.toString()

        //Si el Edit Text esta vacio Mostrar Error
        if(propiedad1.isEmpty()){
            showError()
        }else {
            /*Depenndiendo del Valor obtenido de la funcion getInformation Creara un Poligono
            y Calculara su area*/
            when (namePoligono) {
                "Cuadrado" -> {
                    val cuadrado = Cuadrado(namePoligono,propiedad1.toDouble())
                    cuadrado.setArea()
                    binding.tvInformation.text = cuadrado.sayArea()
                }
                "Triangulo" -> {
                    val triangulo = Triangulo(namePoligono,propiedad1.toDouble(),propiedad2.toDouble())
                    triangulo.setArea()
                    binding.tvInformation.text = triangulo.sayArea()
                }
                "Rectangulo" -> {
                    val rectangulo = Rectangulo(namePoligono,propiedad1.toDouble(),propiedad2.toDouble())
                    rectangulo.setArea()
                    binding.tvInformation.text = rectangulo.sayArea()
                }
                "Circulo" -> {
                    val circulo = Circulo(namePoligono,propiedad1.toDouble())
                    circulo.setArea()
                    binding.tvInformation.text = circulo.sayArea()

                }
            }
        }

    }
    //Funcion encargada de recibir que Poligono selecciono el usuario
    private fun getInformation():String {
        val bundle = intent.extras
        val namePoligono = bundle?.get("NAMEPOLIGONO")
        binding.tvTitlePoligon.text = "Esto es un $namePoligono"

        //Dependiendo de la figura le dara valor a la imagen y a los hint de los EditText
        when(namePoligono){
            "Cuadrado"-> {
                Glide.with(this).load("http://www.calculararea.com/images/figCuadrado.png").into(binding.imageView)
                binding.etPropiedad1.hint = "Ingrese Lado en cm"
                //En caso de que no necesite el parametro2 lo oculto
                binding.etPropiedad2.isInvisible = true
                Glide.with(this).load("https://image.shutterstock.com/image-vector/square-2d-figure-which-all-600w-1986142640.jpg")
                    .into(binding.imageView)
            }
            "Triangulo"-> {
                binding.etPropiedad1.hint = "Ingrese Base en cm"
                binding.etPropiedad2.hint = "Altura cm"
                Glide.with(this).load("https://image.shutterstock.com/image-vector/area-triangle-formula-mathematics-600w-2083364548.jpg").into(binding.imageView)
            }
            "Rectangulo"-> {
                binding.etPropiedad1.hint = "Ingrese Base en cm"
                binding.etPropiedad2.hint = "Altura en cm"
                Glide.with(binding.imageView.context).load("https://image.shutterstock.com/image-vector/area-rectangle-formula-mathematics-600w-2082642475.jpg").into(binding.imageView)
            }
            "Circulo"-> {
                binding.etPropiedad1.hint = "Ingrese Radio en cm"
                binding.etPropiedad2.isInvisible = true
                Glide.with(binding.imageView.context).load("https://image.shutterstock.com/image-vector/perimeter-area-circle-600w-2082431536.jpg").into(binding.imageView)
            }
        }
        return namePoligono.toString()
    }
    //Mostrara un error
    fun showError(){
        Toast.makeText(this,"Error coloque los parametros de la figura", Toast.LENGTH_SHORT).show()
    }
    //Ir al ActivityMain al presionar Atras
    fun getBack(){
        startActivity(Intent(this,MainActivity::class.java))
    }

}