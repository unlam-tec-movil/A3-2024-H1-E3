package ar.edu.unlam.mobile.scaffolding.ui.screens.balance

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun BalanceScreen() {
    Column(modifier = Modifier.padding(start = 8.dp)) {
        MyComponent()
    }
}

@Composable
fun MyComponent() { // funcion para ordenar uno al lado del otro

    val general = 00.00 // simulan las variables de entrada
    val compra = 00.00
    val venta = 00.00

    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text("<    Balance", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = "General", fontSize = 20.sp)
        Text(text = "$ " + general.toString(), fontSize = 50.sp)
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = "Compra", fontSize = 20.sp)
        Text(text = "$ " + compra.toString(), fontSize = 50.sp, color = Color.Red)
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = "Venta", fontSize = 20.sp)
        Text(text = "$ " + venta.toString(), fontSize = 50.sp, color = Color.Green)
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = "Historial", fontSize = 20.sp)
        Box(modifier = Modifier.width(500.dp)) {
            Column(Modifier.width(400.dp)) {
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(text = "imagen  ")
                    Text(text = "Producto  ")
                    Text(text = "unidades")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(text = "imagen  ")
                    Text(text = "Producto  ")
                    Text(text = "unidades")
                }
            }
        }
    } }

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewBalanceScreen() {
    BalanceScreen()
}
