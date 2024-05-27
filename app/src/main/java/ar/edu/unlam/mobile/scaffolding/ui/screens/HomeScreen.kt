package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.ProductoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModelP: ProductoViewModel,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val productos by viewModelP.productos.collectAsState()
    val uiState: HomeUIState by viewModel.uiState.collectAsState()

    when (val helloState = uiState.helloMessageState) {
        is HelloMessageUIState.Loading -> {
            // Loading
        }

        is HelloMessageUIState.Success -> {
            Scaffold(
                topBar = { TopBar() },
                content = { Contenido(productos) },
            )
        }

        is HelloMessageUIState.Error -> {
            // Error
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "") },
        actions = {
            IconButton(onClick = { /* Acción al hacer clic */ }) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* Acción al hacer clic */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
            }
        },
    )
}

@Composable
fun Contenido(productoEntities: List<Producto>) {
    BarraDeBusqueda(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 56.dp)
            .padding(16.dp),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (productoEntities.isNotEmpty()) {
            ProductoList(productoEntities)
        } else {
            Text(
                text = "No hay items",
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}

@Composable
fun BarraDeBusqueda(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Buscar") },
        modifier = modifier,
    )
}

@Composable
fun ProductoList(productoEntities: List<Producto>) {
    LazyColumn {
        items(productoEntities) { producto ->
            ProductoCard(producto)
        }
    }
}

@Composable
fun ProductoCard(productoEntity: Producto) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(text = productoEntity.nombre, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = productoEntity.categoria, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Precio: $${productoEntity.precio}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
