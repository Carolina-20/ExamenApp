package com.example.examenapp.modelos

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class ModeloAutos (private var id: String,
                   private var nombreMod: String,
                   private var precio: Double,
                   private var fuerzaMotor: Double,
                   private var unidadesDisponibles: Int,
                   private var esSedan: Boolean,
                   private var fechaLanzamiento: LocalDate,
                   private var marca: Marca)
{
    constructor() : this("", "",0.0, 0.0, 0, false, LocalDate.now(), Marca())

    fun getId(): String {
        return id
    }

    fun getNombreMod(): String {
        return nombreMod
    }

    fun setNombreMod(nombreMod: String){
        this.nombreMod = nombreMod
    }

    fun getPrecio(): Double {
        return precio
    }

    fun setPrecio(precio: Double){
        this.precio = precio
    }


    fun getFuerzaMotor(): Double {
        return fuerzaMotor
    }

    fun setFuerzaMotor(fuerzaMotor: Double){
        this.fuerzaMotor = fuerzaMotor
    }


    fun getUnidadesDisponibles(): Int {
        return unidadesDisponibles
    }

    fun setUnidadesDisponibles(unidadesDisponibles: Int){
        this.unidadesDisponibles = unidadesDisponibles
    }

    fun getEsSedan(): Boolean {
        return esSedan
    }

    fun setEsSedan(esSedan: Boolean) {
        this.esSedan = esSedan
    }

    fun getFechaLanzamiento(): LocalDate {
        return fechaLanzamiento
    }

    fun setFechaLanzamiento(fechaLanzamiento: LocalDate) {
        this.fechaLanzamiento = fechaLanzamiento
    }



    fun getMarca(): Marca {
        return marca
    }

    fun setMarca(marca: Marca){
        this.marca = marca
    }

    override fun toString(): String {
        return "$nombreMod"
    }

    fun getListaDatos(): List<String>{
        return listOf(
            "Nombre del modelo: $nombreMod",
            "Precio: $precio",
            "Fuerza del motor: $fuerzaMotor",
            "Unidades disponibles: $unidadesDisponibles",
            "Es sedan?: $esSedan",
            "Fecha de lanzamiento: $fechaLanzamiento",
            "Marca del automovil: ${marca.getNombre()}"
        )
    }


}