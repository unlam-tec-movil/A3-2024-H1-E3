package ar.edu.unlam.mobile.scaffolding.ui.screens.crearproducto

import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.crearProducto.CrearProductoViewModel
import kotlinx.coroutines.launch

@Composable
fun CrearProducto(
    controller: NavHostController,
    crearProductoViewModel: CrearProductoViewModel,
) {
    val context = LocalContext.current
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
                    value = crearProductoViewModel.nombre,
                    onValueChange = { crearProductoViewModel.nombre = it },
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
                    value = crearProductoViewModel.precio.toString(),
                    onValueChange = {
                        crearProductoViewModel.precio = it.toDouble()
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
                    text = "Stock (min. 10)",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = crearProductoViewModel.stock.toString(),
                    onValueChange = {
                        crearProductoViewModel.stock = it.toInt()
                        if (crearProductoViewModel.stock >= 10) {
                            crearProductoViewModel.stock = it.toInt()
                        }
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
                    value = crearProductoViewModel.categoria,
                    onValueChange = { crearProductoViewModel.categoria = it },
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
                    value = crearProductoViewModel.qr,
                    onValueChange = { crearProductoViewModel.qr = it },
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
                    value = crearProductoViewModel.nombreProvedor,
                    onValueChange = { crearProductoViewModel.nombreProvedor = it },
                    placeholder = { Text(text = "Nombre del Proovedor") },
                )

                Button(
                    colors =
                        ButtonDefaults.buttonColors(
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
                        ButtonDefaults.buttonColors(
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
                            ButtonDefaults.buttonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = Color.Transparent,
                            ),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier.fillMaxWidth(),
                        enabled =
                            if (
                                crearProductoViewModel.nombre.isEmpty() ||
                                crearProductoViewModel.precio == 0.0 ||
                                crearProductoViewModel.categoria.isEmpty() ||
                                crearProductoViewModel.nombreProvedor.isEmpty() ||
                                crearProductoViewModel.qr.isEmpty()
                            ) {
                                false
                            } else {
                                true
                            },
                        onClick = {
                            if (crearProductoViewModel.stock < 10) {
                                Toast.makeText(context, "El stock debe ser al menos 10", Toast.LENGTH_LONG).show()
                            } else {
                                coroutineScope.launch {
                                    crearProductoViewModel.guardarProducto()
                                    controller.navigate("home")
                                }
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
