package ar.edu.unlam.mobile.scaffolding.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun MyRational(context: Context) {
    val toast =
        Toast.makeText(
            context,
            "La aplicación requiere permisos para realizar esta funcionalidad.",
            Toast.LENGTH_LONG,
        )
    toast.show()
}
