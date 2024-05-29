package ar.edu.unlam.mobile.scaffolding.ui.screens.emptySale

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EmptySaleScreen(controller: NavHostController) {
    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text("<    Vender", fontSize = 20.sp)
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                onClick = { controller.navigate("AgregarVenta") },
                modifier = Modifier.size(64.dp),
                shape = RoundedCornerShape(percent = 50),
                containerColor = Color.DarkGray,
            ) {
                Icon(Icons.Filled.Add, contentDescription = "add")
            }

            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Agregue productos a la venta", fontSize = 20.sp)
            }
        }
    }
}
