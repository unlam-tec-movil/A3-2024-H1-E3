package ar.edu.unlam.mobile.scaffolding.ui.screens.welcome

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.WelcomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(
    onNavigateToHomeScreen: () -> Unit,
    welcomeViewModel: WelcomeViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WelcomeAnimated()
        InputAnimated(
            coroutineScope = coroutineScope,
            onNavigateToHomeScreen = { onNavigateToHomeScreen() },
            welcomeViewModel = welcomeViewModel,
        )
    }
}

@Composable
fun WelcomeAnimated() {
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
fun InputAnimated(
    coroutineScope: CoroutineScope,
    onNavigateToHomeScreen: () -> Unit,
    welcomeViewModel: WelcomeViewModel,
) {
    var currentPage by remember { mutableStateOf("A") }
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        delay(4000)
        visible = true
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
                animationSpec = tween(durationMillis = 1500),
            ),
    ) {
        Crossfade(targetState = currentPage, label = "") { screen ->
            when (screen) {
                "A" -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "Ingrese su nombre")
                        TextField(
                            value = welcomeViewModel.nombre,
                            onValueChange = {
                                welcomeViewModel.nombre = it
                            },
                        )
                        Button(
                            modifier = Modifier.padding(top = 10.dp),
                            shape = RoundedCornerShape(0.dp),
                            onClick = {
                                currentPage = "B"
                            },
                        ) {
                            Text(text = "Continuar")
                        }
                    }
                }
                "B" -> {
                    val texto =
                        "Hola ${welcomeViewModel.nombre}!\n" +
                            "Por favor, ingrese el nombre de su negocio"
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = texto)
                        TextField(
                            value = welcomeViewModel.negocio,
                            onValueChange = {
                                welcomeViewModel.negocio = it
                            },
                        )
                        Button(
                            modifier = Modifier.padding(top = 10.dp),
                            shape = RoundedCornerShape(0.dp),
                            onClick = {
                                coroutineScope.launch {
                                    welcomeViewModel.guardarUsuario()
                                    onNavigateToHomeScreen()
                                }
                            },
                        ) {
                            Text(text = "Continuar")
                        }
                    }
                }
            }
        }
    }
}
