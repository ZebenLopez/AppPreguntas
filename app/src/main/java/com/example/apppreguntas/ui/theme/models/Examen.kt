package com.example.ejercicios1_1.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apppreguntas.R
import com.example.apppreguntas.ui.theme.persistence.PreguntaExamen
import com.example.apppreguntas.ui.theme.utils.leerArchivo


@Composable
fun Examen(navController: NavHostController) {
    val listaPreguntas = leerArchivo(LocalContext.current)
    var index by remember { mutableStateOf(0) }
    val esUltimaPregunta = index == listaPreguntas.lastIndex

    var preguntasAcertadas by remember { mutableStateOf(0) }
    var preguntasFalladas by remember { mutableStateOf(0) }

    if (esUltimaPregunta) {
        AlertDialog(
            onDismissRequest = { /* No hacer nada */ },
            title = { Text("Tu Nota") },
            text = {
                Column {
                    Text("Has sacado un $preguntasAcertadas")
                    Text("Has sacado un $preguntasFalladas")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate("Inicio")
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
    mostrarPregunta(pregunta = listaPreguntas.get(index), indice = { cambiaIndice ->
        index += cambiaIndice
    }, navController = navController)
}


@Composable
fun mostrarPregunta(
    pregunta: PreguntaExamen,
    indice: (Int) -> Unit,
    navController: NavHostController
) {
    Image(
        painter = painterResource(id = R.drawable.principal), contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dificultad: ${pregunta.dificultad}",
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Categoría: ${pregunta.categoria}",
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        var selected by remember { mutableStateOf(false) }
        Text(
            text = pregunta.pregunta,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
            )
        )
        imagen(pregunta)
        respuestasNumeradas(pregunta, selected) { pintarSelected ->
            selected = pintarSelected
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            botonSalir(navController)
            botonSiguiente(pregunta) { cambiaIndice ->
                indice(cambiaIndice)
                selected = false
            }
        }
    }
}

@Composable
fun imagen(pregunta: PreguntaExamen) {
    val imageResourceID = LocalContext.current.resources.getIdentifier(
        pregunta.imagen, "drawable", LocalContext.current.packageName
    )
    Image(
        painter = painterResource(id = imageResourceID),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.30f)
            .padding(16.dp)
    )
}

@Composable
fun respuestasNumeradas(
    pregunta: PreguntaExamen,
    seleccionada: Boolean,
    pintar: (Boolean) -> Unit
) {

    val opciones = listOf(
        pregunta.respuesta1,
        pregunta.respuesta2,
        pregunta.respuesta3,
        pregunta.respuesta4
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        opciones.forEach { opcion ->
            BotonRespuesta(
                opcion,
                pregunta,
                seleccionada
            ) { onSelectedChange ->
                pintar(onSelectedChange)
            }
        }
    }
}

@Composable
fun BotonRespuesta(
    text: String,
    pregunta: PreguntaExamen,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    var color by remember {
        mutableStateOf(Color.Blue)
    }
    var acierto by remember { mutableStateOf(0) }
    var fallo by remember { mutableStateOf(0) }

    if (!selected) {
        color = Color(255, 154, 8, 450)
    }
    Button(
        onClick = {
            if (!selected) {
                if (text.equals(pregunta.respuestaCorrecta)) {
                    acierto++
                } else {
                    fallo++
                }
                    color = if (text.equals(pregunta.respuestaCorrecta)) {
                        Color.Green
                    } else {
                        Color.Red
                    }
            }
            onSelectedChange(true)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(50))
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun botonSiguiente(pregunta: PreguntaExamen, indice: (Int) -> Unit) {
    Button(
        onClick = { indice(1) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(39, 239, 245, 450)
        ),
        modifier = Modifier
            .padding(16.dp)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(50))
    ) {
        Text(
            text = "Siguiente",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Flecha adelante"
        )
    }
}

@Composable
fun botonSalir(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("Inicio") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(39, 239, 245, 450)
        ),
        modifier = Modifier
            .padding(16.dp)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(50))
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Flecha atras"
        )
        Text(
            text = "Salir",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )
    }
}

