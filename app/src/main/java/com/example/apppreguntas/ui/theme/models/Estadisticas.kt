package com.example.apppreguntas.ui.theme.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.apppreguntas.R

@Composable
fun Estadisticas(navController : NavHostController){
        Image(
            painter = painterResource(id = R.drawable.trabajando), contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
}