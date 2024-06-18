package ar.edu.unlam.mobile.scaffolding.ui.screens.venta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ListaProductosVenta(
    controller: NavHostController,
    viewModel: ProductoViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = { MyTopBar(onNavigateBack = { controller.popBackStack() }, title = "Vender") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { controller.navigate("agregarProductoVenta") },
            ) {
                Text(text = "Agregar producto a la lista")
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            if (viewModel.listaVenta.isEmpty()) {
                Text(text = "Agregue items para vender")
            } else {
                LazyColumn {
                    items(items = viewModel.listaVenta) {
                        ListaDeVentaItem(it)
                    }
                }
            }
            Button(onClick = {
                coroutineScope.launch {
                    viewModel.vender()
                    controller.popBackStack()
                }
            }) {
                Text(text = "Realizar venta")
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
                placeholder = painterResource(R.drawable.splashimg),
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
