package ar.edu.unlam.mobile.scaffolding.ui.screens.addStock

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddStockScreen(){
    var selProduct by remember{ mutableStateOf("") }
    var cantProduct by remember{ mutableStateOf("") }
    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text("<    Agregar Stock", fontSize = 20.sp)
         Spacer(modifier = Modifier.height(40.dp))
        TextField(value = selProduct, onValueChange = {selProduct = it},
            label = { Text(text = "Seleccione el producto")})
         Spacer(modifier = Modifier.height(22.dp))
        TextField(value = cantProduct, onValueChange = {cantProduct = it},
            label = { Text(text = "Cantidad")})
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Agregar")
            }
             }

         }

}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAddStockScreen(){
    AddStockScreen()
}