package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InputBusiness (){//(navController: NavController){
    //Scaffold {
        SecondBodyContent()//(navController)
  //  }
}

@Composable
fun SecondBodyContent(){//(navController: NavController){
// INGRESAR name POR PARAMETRO
    val name ="USUARIO"
    var dateImput2 by remember{ mutableStateOf("") }





    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "BIENVENIDO " + name,color= Color.Cyan)
        Spacer(modifier = Modifier.height(16.dp))
     TextField(value = dateImput2,
            onValueChange = { dateImput2 = it },
            label = { Text(text = "INGRESE NOMBRE DE SU NEGOCIO") },
            keyboardOptions = KeyboardOptions
                (capitalization = KeyboardCapitalization.Characters),
            singleLine = true)
        Button(onClick = {
            //pasar dato a RUM nombre del negocio
            //navController.popBackStack() // pasar a pantalla Home
        }) {
            Text(text = "SIGUIENTE")
        }
    }
}

@Preview
@Composable
fun prevInBus(){
    InputBusiness()
}