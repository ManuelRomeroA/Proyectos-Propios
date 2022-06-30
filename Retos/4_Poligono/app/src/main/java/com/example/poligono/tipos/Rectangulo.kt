package com.example.poligono.tipos

class Rectangulo(
    name:String,
    propiedad1:Double,
    propiedad2:Double,
    override val img:String = "http://www.calculararea.com/images/figRectangulo.png"):Triangulo(name, propiedad1, propiedad2, img) {

    override fun setArea(): Double = propiedad1*propiedad2
    override fun sayArea(): String = "El area de un $name con una base de $propiedad1 cm y una altura" +
            "de $propiedad2 cm es de ${setArea()} cm^2"
}