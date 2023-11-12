package com.example.apppreguntas.ui.theme.models

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.apppreguntas.R

@Composable
fun AgnadirPreguntas(navController: NavHostController){
    agregarPregunta()
}


@Composable
fun agregarPregunta(){
    Image(
        painter = painterResource(id = R.drawable.trabajando), contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}