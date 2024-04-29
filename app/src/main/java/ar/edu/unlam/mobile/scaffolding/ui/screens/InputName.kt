package ar.edu.unlam.mobile.scaffolding.ui.screens

 import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InputName (){//(navController: NavController){
   // Scaffold {
        BodyContent()//(navController)
   // }
}

@Composable
fun BodyContent(){//(navController: NavController){
    var dateImput by remember{ mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Text(text = "PANTALLA UNO")

        TextField(value = dateImput, onValueChange = {dateImput = it},
            label = { Text(text = "INGRESE SU NOMBRE")} ,
            keyboardOptions = KeyboardOptions
                (capitalization = KeyboardCapitalization.Characters),
            singleLine = true)
        Button(onClick = {
            // GRABAR DATO EN RUN (dateImput) o pasarlo a pantalla siguiente
            //navController.navigate(route = AppScreens.SecondScreen.route)
        }) {
            Text(text = "SIGUIENTE")
        }
    }
}
@Preview
@Composable
fun prevInName(){
    InputName()
}