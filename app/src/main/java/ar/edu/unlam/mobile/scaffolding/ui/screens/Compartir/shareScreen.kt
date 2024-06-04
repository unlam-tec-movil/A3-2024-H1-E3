package ar.edu.unlam.mobile.scaffolding.ui.screens.Compartir

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext


@Composable
fun ShareScreen(){

    val context = LocalContext.current
    val shareLauncher = (context as ComponentActivity).registerForActivityResult(
       ActivityResultContracts.StartActivityForResult()) { }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(onClick = {
           val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,
                    " Mensaje compartido desde mi aplicación")

               type =   "text/plain"
            }
           val chooser = Intent.createChooser(sendIntent, "")
            shareLauncher.launch(chooser)
        }) {
            Text(text = "COMPARTIR")
        }

    }


    }


