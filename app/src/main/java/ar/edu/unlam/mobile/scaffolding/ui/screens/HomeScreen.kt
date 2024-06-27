package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.myHome.MyHomeViewModel
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: MyHomeViewModel,
    controller: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val productos by homeViewModel.productos.collectAsState()
    val uiState: HomeUIState by viewModel.uiState.collectAsState()
    var searchText by remember { mutableStateOf("") }

    when (val helloState = uiState.helloMessageState) {
        is HelloMessageUIState.Loading -> {
            // Loading
        }

        is HelloMessageUIState.Success -> {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DrawerContent(
                        controller,
                        onMenuItemClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        },
                    )
                },
                content = {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onMenuClick = {
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                },
                            )
                        },
                        content = { paddingValues ->
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues),
                            ) {
                                BarraDeBusqueda(
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                    searchText = searchText,
                                    onSearchTextChange = { newText ->
                                        searchText = newText
                                    },
                                )
                                // Filtrar productos según el texto de búsqueda
                                val filteredProducts =
                                    productos.filter { producto ->
                                        producto.nombre.contains(searchText, ignoreCase = true)
                                    }
                                Contenido(filteredProducts, onProductoClick = { producto ->
                                    // Maneja el clic del producto aquí
                                    homeViewModel.seleccionarProducto(producto)
                                    controller.navigate("detalle")
                                })
                            }
                        },
                    )
                },
            )
        }

        is HelloMessageUIState.Error -> {
            // Error
        }
    }
}

@Composable
fun Contenido(
    productoEntities: List<Producto>,
    onProductoClick: (Producto) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        if (productoEntities.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(productoEntities) { producto ->
                    ProductoItem(
                        producto = producto,
                        onClick = { onProductoClick(producto) },
                        isLowStock = producto.stock in 5..10,
                    )
                }
            }
        } else {
            Text(
                text = "No hay items",
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
fun BarraDeBusqueda(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
) {
    TextField(
        value = searchText,
        onValueChange = { newText -> onSearchTextChange(newText) },
        placeholder = { Text("Buscar") },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun itemPreview() {
    val producto =
        Producto(
            nombre = "Prueba",
            precio = 200.0,
            stock = 10,
            fotoUri = "file://prueba",
            qr = "",
            ubicacionProveedor = LatLng(1.0, 1.0),
            nombreProvedor = "",
            categoria = "",
        )
    val onClick = { print("Click") }
    ProductoItem(producto = producto, onClick = onClick)
}

@Composable
fun ProductoItem(
    producto: Producto,
    onClick: () -> Unit,
    isLowStock: Boolean = false,
) {
    Card(
        modifier =
            Modifier
                .padding(8.dp)
                .aspectRatio(1.0f)
                .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(0.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .height(95.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(0.dp)),
                model = producto.fotoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.splashimg),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (isLowStock) {
                Text(
                    text = "Stock bajo: ${producto.stock}",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
    )
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    onMenuItemClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier =
            Modifier
                .fillMaxHeight()
                .width(300.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tienda",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            val menuItems =
                listOf(
                    "Inicio" to "home",
                    "Agregar stock" to "agregarStock",
                    "Vender" to "listaVenta",
                )

            menuItems.forEach { (menuItem, route) ->
                Text(
                    text = menuItem,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                navController.navigate(route)
                                onMenuItemClick()
                            },
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
