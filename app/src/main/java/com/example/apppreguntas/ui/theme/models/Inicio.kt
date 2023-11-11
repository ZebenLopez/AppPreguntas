package com.example.apppreguntas.ui.theme.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apppreguntas.R
import com.example.apppreguntas.ui.theme.rutas.Rutas

@Composable
fun PantallaInicio(navController: NavHostController?) {
    Image(
        painter = painterResource(id = R.drawable.principal), contentDescription = null,
        contentScale = ContentScale.FillWidth
    ) //me llena la pantalla con la imagen
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
//            .padding(16.dp)
            .fillMaxHeight()
    ) {
        formatoTexto(
            texto = "Bienvenido a la app de preguntas y respuestas para la clase de PLG.",
            tamanio = 18.sp, color = Color.White
        )
        formatoTexto(texto = "Elegir un formato de juego.", tamanio = 24.sp, color = Color.White)
        formatoBoton(texto = "Estandar",
            onClick = {
                navController?.navigate(Rutas.Estandar.ruta)
            })
        formatoBoton(texto = "Examen",
            onClick = {
                navController?.navigate(Rutas.Examen.ruta)
            })
        formatoTexto(texto = "Otras opciones", tamanio = 24.sp, color = Color.White)
        formatoBoton(texto = "Estadísticas",
            onClick = {
                navController?.navigate(Rutas.Estadisticas.ruta)
            })
        formatoBoton(texto = "Añadir Preguntas",
            onClick = {
                navController?.navigate(Rutas.AgnadirPreguntas.ruta)
            })
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun VerInicio() {
    PantallaInicio(navController = null)
}

@Composable //formato de textos
fun formatoTexto(texto: String, tamanio: TextUnit, color: Color) {
    Text(
        text = texto,
        fontSize = tamanio,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier
            .padding(20.dp)
    )
}

@Composable

fun formatoBoton(
    texto: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(255, 154, 8, 150)
        ),
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = texto,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }
}