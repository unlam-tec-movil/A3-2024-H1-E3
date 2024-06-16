package ar.edu.unlam.mobile.scaffolding.ui.screens.venta

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AgregarProductoVenta(
    controller: NavHostController,
    viewModel: ProductoViewModel,
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopBar(
                onNavigateBack = { controller.popBackStack() },
                title = "",
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .padding(padding),
        ) {
            Button(onClick = { controller.navigate("qr") }) {
                Text(text = "Seleccionar por QR")
            }
            TextField(
                value = viewModel.newStock.toString(),
                onValueChange = { viewModel.newStock = it.toInt() },
                placeholder = { Text(text = "Cantidad de stock a vender") },
                keyboardOptions =
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.agregarProductoVenta()
                        controller.popBackStack()
                    }
                },
            ) {
                Text(text = "Agregar a la lista")
            }
        }
    }
}
