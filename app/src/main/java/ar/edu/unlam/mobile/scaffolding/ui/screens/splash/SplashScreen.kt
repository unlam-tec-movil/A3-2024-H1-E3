package ar.edu.unlam.mobile.scaffolding.ui.screens.splash

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.UsuarioViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.UsuarioViewModelProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    onNavigateToWelcomeScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    viewModel: UsuarioViewModel = viewModel(factory = UsuarioViewModelProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()

    var cantidadUsuarios = 0
    LaunchedEffect(cantidadUsuarios) {
        coroutineScope.launch {
            cantidadUsuarios = viewModel.getCantidadUsuarios()
        }
    }

    LaunchedEffect(key1 = true) {
        delay(1000)
        Log.i("Cantidad usuarios", "$cantidadUsuarios")
        if (cantidadUsuarios == 0) {
            onNavigateToWelcomeScreen()
        } else {
            onNavigateToHomeScreen()
        }
    }

    Scaffold {
        BodycontentSplash()
    }
}

@Composable
fun BodycontentSplash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var showLogo by remember { mutableStateOf(false) }

        LaunchedEffect(showLogo) {
            showLogo = true
        }

        AnimatedVisibility(
            visible = showLogo,
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
        ) {
            Image(
                painter = painterResource(id = R.drawable.splashimg),
                contentDescription = "logo",
                modifier = Modifier.size(150.dp),
            )
        }
    }
}
