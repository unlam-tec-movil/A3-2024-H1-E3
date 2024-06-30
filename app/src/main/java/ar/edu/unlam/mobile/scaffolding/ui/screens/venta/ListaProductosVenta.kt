package ar.edu.unlam.mobile.scaffolding.ui.screens.venta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock.VenderProductosViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ListaProductosVenta(
    controller: NavHostController,
    viewModel: VenderProductosViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val listaVenta by viewModel.listaVenta.collectAsState()

    Scaffold(
        topBar = {
            MyTopBar(
                onNavigateBack = { controller.popBackStack() },
                title = "Vender",
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                onClick = { controller.navigate("agregarProductoVenta") },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "add")
            }
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
            if (listaVenta.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Lista vacia",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                )
            } else {
                LazyColumn {
                    items(items = listaVenta) {
                        ListaDeVentaItem(it)
                    }
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                enabled = listaVenta.isNotEmpty(),
                onClick = {
                    coroutineScope.launch {
                        viewModel.vender()
                        controller.popBackStack()
                    }
                },
            ) {
                Text(text = "Finalizar")
            }
        }
    }
}

@Composable
private fun ListaDeVentaItem(item: Producto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row {
            AsyncImage(
                modifier =
                    Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .padding(5.dp),
                model = item.fotoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.noimage),
            )
            Column(
                modifier = Modifier.padding(top = 15.dp, start = 10.dp),
            ) {
                Text(
                    text = item.nombre,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "Cantidad: ${item.stock}")
            }
        }
    }
}
