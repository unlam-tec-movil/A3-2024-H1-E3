package ar.edu.unlam.mobile.scaffolding.ui.screens.splash

import android.annotation.SuppressLint
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    onNavigateToWelcomeScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        onNavigateToWelcomeScreen()
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
