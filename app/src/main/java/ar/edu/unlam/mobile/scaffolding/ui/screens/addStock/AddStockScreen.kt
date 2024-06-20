package ar.edu.unlam.mobile.scaffolding.ui.screens.addStock

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.sensorViewModel.Sensor
import kotlinx.coroutines.launch

@Preview
@Composable
private fun MyPreview() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(context, InventoryDatabase::class.java, "producto_db").build()
    val dao = db.producotDao()
    val repository = OfflineProductoRepository(dao)
    val viewModel = ProductoViewModel(repository)
    val navController: NavHostController = rememberNavController()

    AddStockScreen(navController, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddStockScreen(
    controller: NavHostController,
    viewModel: ProductoViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val sensor =
        remember {
            Sensor(context) {
                coroutineScope.launch {
                    controller.navigate("qr")
                }
            }
        }

    DisposableEffect(Unit) {
        onDispose {
            sensor.unregister()
        }
    }

    Scaffold(
        topBar = {
            MyTopBar(
                onNavigateBack = { controller.popBackStack() },
                title = "Agregar stock",
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(start = 10.dp)
                    .padding(end = 10.dp)
                    .fillMaxSize(),
        ) {
            Text(
                text = "Seleccione un producto por QR:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                onClick = { controller.navigate("qr") },
            ) {
                Text(text = "Escanear")
            }
            Text(
                text = "Cantidad de stock a agregar:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.newStock.toString(),
                onValueChange = {
                    viewModel.newStock = it.toInt()
                    viewModel.newStock = it.toIntOrNull() ?: 0
                },
                placeholder = { Text(text = "Cantidad de stock a agregar") },
                keyboardOptions =
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
            )
            Box(contentAlignment = Alignment.BottomCenter) {
                Button(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                    shape = RoundedCornerShape(0.dp),
                    onClick = {
                        coroutineScope.launch {
                            viewModel.agregarStock()
                            controller.popBackStack()
                        }
                    },
                ) {
                    Text(text = "Finalizar")
                }
            }
        }
    }
}
