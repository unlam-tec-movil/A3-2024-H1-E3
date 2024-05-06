package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.R
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(){//(navController: NavController){


    LaunchedEffect(key1 = true) {
        delay(5000)

    //   navController.navigate(AppScreens.welcome-Screen.route)
    }

    Scaffold {
    BodycontentSplash()//(navController)
     }

    }

@Composable
fun BodycontentSplash(){//(navController: NavController) {



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(painter = painterResource(id = R.drawable.splashimg), contentDescription = "logo")
        Text(text = "BIENVENIDO", fontSize = 40.sp)
    }
}

@Preview
@Composable
fun previewSplash(){
    SplashScreen()
}