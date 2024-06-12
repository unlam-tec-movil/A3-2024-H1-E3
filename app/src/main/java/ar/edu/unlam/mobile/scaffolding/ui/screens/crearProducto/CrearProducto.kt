package ar.edu.unlam.mobile.scaffolding.ui.screens.crearProducto

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

@Composable
fun CrearProducto(
    controller: NavHostController,
    viewModel: ProductoViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier =
            Modifier
                // .background(color = Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    ) {
        Card(
            // colors = CardDefaults.cardColors(containerColor = Color.White),
            // shape = RoundedCornerShape(0.dp),
            modifier =
                Modifier
                    .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        ) {
            IconButton(
                modifier =
                    Modifier
                        .padding(5.dp),
                onClick = {
                    controller.navigate(route = "home")
                },
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null,
                    // tint = Color.Black,
                    modifier =
                        Modifier
                            .size(45.dp),
                )
            }
        } // CIERRA CARD PARA ATRAS

        Column(modifier = Modifier) {
            Text(
                text = "Agregar Producto",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                // color = Color(39, 40, 41),
                fontSize = 22.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 1.dp),
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Especificaciones:",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                // color = Color.Black,
                text = "Nombre:",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(10.dp),
            )
            TextField(
                modifier =
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                // .background(Color.White),
                value = viewModel.nombre,
                onValueChange = { viewModel.nombre = it },
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                // color = Color.Black,
                text = "Precio:",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(10.dp),
            )
            TextField(
                modifier =
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                // .background(Color.White),
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
            Spacer(modifier = Modifier.height(15.dp))

            Card(
                // colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier =
                    Modifier
                        // .background(color = Color.White)
                        .padding(10.dp)
                        .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Adicional",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(10.dp),
                        // color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                        // .background(Color.White),
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

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()

                    TextField(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                        // .background(Color.White),
                        value = viewModel.categoria,
                        onValueChange = { viewModel.categoria = it },
                        placeholder = { Text(text = "Categoria") },
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                    TextField(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                        // .background(Color.White),
                        value = viewModel.nombreProvedor,
                        onValueChange = { viewModel.nombreProvedor = it },
                        placeholder = { Text(text = "Nombre del Proovedor") },
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                }
            }

            Card(
                // colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier =
                    Modifier
                        // .background(color = Color.White)
                        .padding(10.dp)
                        .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    //  .background(Color.White),
                    value = viewModel.qr,
                    onValueChange = { viewModel.qr = it },
                    placeholder = { Text(text = "Generar GR") },
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            Box(
                modifier =
                    Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.fillMaxWidth(),
                    // colors = ButtonDefaults.buttonColors(Color(39, 40, 41)),
                    onClick = {
                        controller.navigate("indicarUbi")
                    },
                ) {
                    Text(
                        "Indicar ubicación del proveedor",
                        fontSize = 17.sp,
                        // color = Color.White,
                    )
                }
            }
            Box(
                modifier =
                    Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.fillMaxWidth(),
                    // colors = ButtonDefaults.buttonColors(Color(39, 40, 41)),
                    onClick = {
                        controller.navigate("Camara")
                    },
                ) {
                    Text(
                        "Agregar foto",
                        fontSize = 17.sp,
                        // color = Color.White,
                    )
                }
            }
            Box(
                modifier =
                    Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
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
                        // color = Color.White,
                    )
                }
            }
        }
    } // CIERRA EL PRIMER COLUMN
} // CIERRA LA FUNCION
