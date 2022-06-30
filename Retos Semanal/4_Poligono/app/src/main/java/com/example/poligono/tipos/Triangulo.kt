package com.example.poligono.tipos

open class Triangulo(
    name:String,
    propiedad1:Double,
    val propiedad2:Double,
    img:String = "http://www.calculararea.com/images/figTriangulo.png"):Cuadrado(name,propiedad1, img) {
    override fun setArea(): Double = (propiedad1*propiedad2)/2
    override fun sayArea(): String = "El area de un $name con base $propiedad1 cm y con una altura" +
            "de $propiedad2 cm es de ${setArea()} cm^2"

}