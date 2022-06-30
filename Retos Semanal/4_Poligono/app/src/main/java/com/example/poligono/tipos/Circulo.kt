package com.example.poligono.tipos
import java.lang.Math.PI
import kotlin.math.pow

class Circulo(
    override val name: String,
    override val propiedad1: Double,
    override val img:String = "http://www.calculararea.com/images/figCirculo.png"):Cuadrado(name, propiedad1, img){

    override fun setArea(): Double = PI*(propiedad1.pow(2))
    override fun sayArea(): String = "El area del circulo con radio $propiedad1 cm " +
            "es igual a ${setArea()} cm^2"
}