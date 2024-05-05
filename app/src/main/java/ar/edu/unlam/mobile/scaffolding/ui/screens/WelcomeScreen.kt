package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen() {
    var name = ""
    var business = ""

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WelcomeAnimated()
        InputAnimated {
            name = it.first()
            business = it.last()
        }
    }
}

@Composable
private fun WelcomeAnimated() {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(visible) {
        delay(2000)
        visible = false
    }

    AnimatedVisibility(
        visible = visible,
        enter =
            fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f,
                animationSpec = tween(durationMillis = 1000),
            ),
        exit =
            fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 1000),
            ),
    ) {
        Text(text = "Bienvenido!")
    }
}

@Composable
private fun InputAnimated(callback: (List<String>) -> Unit) {
    var nameIsVisible by remember { mutableStateOf(false) }
    var businessIsVisible by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var business by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(nameIsVisible) {
        delay(4000)
        nameIsVisible = !businessIsVisible
    }

    AnimatedVisibility(
        visible = nameIsVisible,
        enter =
            fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f,
                animationSpec = tween(durationMillis = 1000),
            ),
        exit =
            fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 1500),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Ingrese su nombre")
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
            )
            Button(onClick = {
                nameIsVisible = false
                businessIsVisible = true
            }) {
                Text(text = "Continuar")
            }
        }
    }

    AnimatedVisibility(
        visible = businessIsVisible,
        enter =
            fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f,
                animationSpec = tween(durationMillis = 2000),
            ),
        exit =
            fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 1500),
            ),
    ) {
        val texto =
            "Hola ${name.text}!\n" +
                "Por favor, ingrese el nombre de su negocio"
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = texto)
            TextField(
                value = business,
                onValueChange = {
                    business = it
                },
            )
            Button(onClick = {
                businessIsVisible = false
                val dataValues: List<String> = listOf(name.text, business.text)
                callback(dataValues)
            }) {
                Text(text = "Continuar")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    WelcomeScreen()
}
