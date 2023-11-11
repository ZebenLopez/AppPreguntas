package com.example.apppreguntas.ui.theme.utils

import android.content.Context
import com.example.apppreguntas.ui.theme.persistence.PreguntaExamen
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun leerArchivo(contexto: Context): List<PreguntaExamen> {
    val cuestiones: MutableList<PreguntaExamen> = ArrayList()
    try {
        val assetManager = contexto.assets
        val inputStream = assetManager.open("Preguntas.txt")
        val lector = BufferedReader(InputStreamReader(inputStream))

        var contador = -1
        var dificultad: String = ""
        var categoria: String = ""
        var imagen: String = ""
        var pregunta: String = ""
        var respuesta1: String = ""
        var respuesta2: String = ""
        var respuesta3: String = ""
        var respuesta4: String = ""
        var respuestaCorrecta: String = ""

        lector.forEachLine { linea ->
            if (linea.isNotBlank()) {
                contador++
                when (contador) {
                    0 -> dificultad = linea
                    1 -> categoria = linea
                    2 -> pregunta = linea
                    3 -> imagen = linea
                    4 -> respuesta1 = linea
                    5 -> respuesta2 = linea
                    6 -> respuesta3 = linea
                    7 -> respuesta4 = linea
                    8 -> {
                        respuestaCorrecta = linea
                        cuestiones.add(
                            PreguntaExamen(
                                dificultad,
                                categoria,
                                pregunta,
                                imagen,
                                respuesta1,
                                respuesta2,
                                respuesta3,
                                respuesta4,
                                respuestaCorrecta
                            )
                        )
                        contador = -1
                    }
                }

            }
        }
        lector.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return cuestiones
}
