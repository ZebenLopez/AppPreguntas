package com.example.apppreguntas.ui.theme.models

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun AgnadirPreguntas(navController: NavHostController){
    agregarPregunta()
}


@Composable
fun agregarPregunta(){
    var dificultad = remember { mutableSetOf("")}
    Text(text = "Caca")
}