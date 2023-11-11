package com.example.apppreguntas.ui.theme.persistence

data class PreguntaExamen(
    val dificultad: String,
    val categoria: String,
    val pregunta: String,
    val imagen: String,
    val respuesta1: String,
    val respuesta2: String,
    val respuesta3: String,
    val respuesta4: String,
    val respuestaCorrecta: String
)
