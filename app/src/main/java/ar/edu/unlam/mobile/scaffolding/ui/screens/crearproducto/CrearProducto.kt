package ar.edu.unlam.mobile.scaffolding.ui.screens.crearproducto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
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

    CrearProducto(navController, viewModel)
}

@Composable
fun CrearProducto(
    controller: NavHostController,
    viewModel: ProductoViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = { MyTopBar(onNavigateBack = { controller.popBackStack() }, title = "Agregar item") },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(10.dp)
                    .padding(end = 10.dp)
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column {
                Text(
                    text = "Nombre:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = viewModel.nombre,
                    onValueChange = { viewModel.nombre = it },
                )
                Text(
                    text = "Precio:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = viewModel.textP,
                    onValueChange = {
                        viewModel.textP = it
                        viewModel.precio = it.toDoubleOrNull() ?: 0.0
                    },
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                        ),
                )
                Text(
                    text = "Adicional",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "Stock",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = viewModel.textS,
                    onValueChange = {
                        viewModel.textS = it
                        viewModel.stock = it.toIntOrNull() ?: 0
                    },
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                        ),
                    placeholder = { Text(text = "Stock") },
                )
                Text(
                    text = "Categoria",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = viewModel.categoria,
                    onValueChange = { viewModel.categoria = it },
                    placeholder = { Text(text = "Categoria") },
                )
                Text(
                    text = "Codigo QR",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = viewModel.qr,
                    onValueChange = { viewModel.qr = it },
                    placeholder = { Text(text = "Codigo") },
                )
                Text(
                    text = "Proveedor",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = viewModel.nombreProvedor,
                    onValueChange = { viewModel.nombreProvedor = it },
                    placeholder = { Text(text = "Nombre del Proovedor") },
                )

                Button(
                    colors =
                        ButtonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent,
                        ),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        controller.navigate("indicarUbi")
                    },
                ) {
                    Text(
                        "Indicar ubicación del proveedor",
                        fontSize = 17.sp,
                    )
                }
                Text(
                    text = "Foto",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Button(
                    colors =
                        ButtonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent,
                        ),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        controller.navigate("Camara")
                    },
                ) {
                    Text(
                        "Agregar foto",
                        fontSize = 17.sp,
                    )
                }
                Box(contentAlignment = Alignment.BottomCenter) {
                    Button(
                        colors =
                            ButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = Color.Transparent,
                            ),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutineScope.launch {
                                viewModel.guardarProducto()
                                controller.navigate("home")
                            }
                        },
                    ) {
                        Text(
                            "AGREGAR",
                            fontSize = 17.sp,
                        )
                    }
                }
            }
        }
    }
}
