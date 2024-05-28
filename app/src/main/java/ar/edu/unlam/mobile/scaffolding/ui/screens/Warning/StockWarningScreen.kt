package ar.edu.unlam.mobile.scaffolding.ui.screens.Warning

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StockWarningScreen() { // (navController: NavController){

    Scaffold {
        BodyContentWarning() // (navController)
    }
}

@Composable
fun BodyContentWarning() { // (navController: NavController){
    var editable by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(2000)
        editable = true
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.warning),
            contentDescription = "Warning",
            Modifier.size(120.dp),
        )
        Text(text = "ATENCION", fontSize = 30.sp)
        Text(text = "Hay productos con poco stock", fontSize = 20.sp)
        AnimatedVisibility(visible = editable) {
            Button(onClick = {
                // ir a Home
            }) {
                Text(text = "HOME")
            }
        }
    }
}

@Preview
@Composable
fun warningPreview() {
    StockWarningScreen()
}
