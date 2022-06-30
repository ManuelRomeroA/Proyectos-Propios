package com.example.poligono.tipos


open class Cuadrado(
    open val name: String,
    open val propiedad1:Double,
    open val img:String = "http://www.calculararea.com/images/figCuadrado.png"){

    open fun setArea(): Double {
        return propiedad1 * propiedad1
    }
    open fun sayArea(): String {
        return "El $name con lados de $propiedad1 cm es de ${setArea()} cm^2"
    }
}