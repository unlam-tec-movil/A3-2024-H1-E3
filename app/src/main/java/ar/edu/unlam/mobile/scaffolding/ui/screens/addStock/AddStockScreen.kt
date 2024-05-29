package ar.edu.unlam.mobile.scaffolding.ui.screens.addStock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun AddStockScreen(controller: NavHostController) {
    var selProduct by remember { mutableStateOf("") }
    var cantProduct by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text("<    Agregar Stock", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            value = selProduct,
            onValueChange = { selProduct = it },
            label = { Text(text = "Seleccione el producto") },
        )
        Spacer(modifier = Modifier.height(22.dp))
        TextField(
            value = cantProduct,
            onValueChange = { cantProduct = it },
            label = { Text(text = "Cantidad") },
        )
        Text(
            text = "Escanear",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(10.dp),
        )
        IconButton(
            modifier = Modifier
                .shadow(12.dp, CardDefaults.shape)
                // .background(Color.DarkGray)
                .padding(3.dp),
            onClick = {
                controller.navigate("qr")
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp),
            )
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Agregar")
            }
        }
    }
}
