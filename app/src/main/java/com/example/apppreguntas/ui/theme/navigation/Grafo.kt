package com.example.apppreguntas.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apppreguntas.ui.theme.models.AgnadirPreguntas
import com.example.apppreguntas.ui.theme.models.Estadisticas
import com.example.apppreguntas.ui.theme.models.Estandar
import com.example.apppreguntas.ui.theme.models.PantallaInicio
import com.example.apppreguntas.ui.theme.rutas.Rutas
import com.example.ejercicios1_1.componentes.Examen


@Composable
fun GrafoNavegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.Inicio.ruta){

        composable(Rutas.Inicio.ruta){
            PantallaInicio(navController = navController)
        }

        composable(Rutas.Estandar.ruta){
            Estandar(navController = navController)
        }

        composable(Rutas.Examen.ruta){
            val preguntasAcertadas = remember { mutableStateOf(0) }
            val preguntasFalladas = remember { mutableStateOf(0) }
            Examen(navController = navController,
                preguntasAcertadas = preguntasAcertadas, preguntasFalladas = preguntasFalladas)
        }

        composable(Rutas.Estadisticas.ruta){
            Estadisticas(navController = navController)
        }

        composable(Rutas.AgnadirPreguntas.ruta){
            AgnadirPreguntas(navController = navController)
        }
    }


}
