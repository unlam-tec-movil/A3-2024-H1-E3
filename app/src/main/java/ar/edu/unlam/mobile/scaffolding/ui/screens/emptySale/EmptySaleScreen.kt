package ar.edu.unlam.mobile.scaffolding.ui.screens.emptySale

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptySaleScreen(){
    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text("<    Vender", fontSize = 20.sp)
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {

            Button(onClick = { /*TODO*/ }) {
                Text(text = "+")
            }

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Text("Agregue productos a la venta", fontSize = 20.sp)
        }

        }

    }
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewEmptySaleScreen(){
   EmptySaleScreen()
}