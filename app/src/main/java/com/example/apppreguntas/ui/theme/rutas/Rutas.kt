package com.example.apppreguntas.ui.theme.rutas

sealed class Rutas (val ruta : String) {
    object Inicio : Rutas ("Inicio")
    object Estandar : Rutas ("Estandar")
    object Examen : Rutas ("Examen")
    object Estadisticas : Rutas ("Estadisticas")
    object AgnadirPreguntas : Rutas ("AñadirPreguntas")
}