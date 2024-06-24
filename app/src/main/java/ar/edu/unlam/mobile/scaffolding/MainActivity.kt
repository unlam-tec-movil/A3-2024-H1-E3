package ar.edu.unlam.mobile.scaffolding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.stockViewModel.AgregarStockViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.DetalleProducto
import ar.edu.unlam.mobile.scaffolding.ui.screens.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.addStock.AddStockScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.balance.BalanceScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.camera.CameraScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.configuracion.Configuracion
import ar.edu.unlam.mobile.scaffolding.ui.screens.crearproducto.CrearProducto
import ar.edu.unlam.mobile.scaffolding.ui.screens.map.MapProviderScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.map.MapScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.qrScanner.QRScannerScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.splash.SplashScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.venta.AgregarProductoVenta
import ar.edu.unlam.mobile.scaffolding.ui.screens.venta.ListaProductosVenta
import ar.edu.unlam.mobile.scaffolding.ui.screens.welcome.WelcomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val viewModel: ProductoViewModel = hiltViewModel()
                    val startDestination = "splash-screen"
                    MyAppNavHost(startDestination = startDestination, viewModel)
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    startDestination: String,
    viewModel: ProductoViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("splash-screen") {
            SplashScreen(
                onNavigateToWelcomeScreen = {
                    navController.navigate("welcome-screen")
                },
                onNavigateToHomeScreen = {
                    navController.navigate("main-screen")
                },
            )
        }
        composable("welcome-screen") {
            WelcomeScreen(
                onNavigateToHomeScreen = {
                    navController.navigate("main-screen")
                },
            )
        }
        composable("main-screen") { MainScreen(viewModel) }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(viewModel: ProductoViewModel) {
    // Controller es el elemento que nos permite navegar entre pantallas. Tiene las acciones
    // para navegar como naviegate y también la información de en dónde se "encuentra" el usuario
    // a través del back stack

    val controller = rememberNavController()
    // State to keep track of the current route
    val currentRoute = remember { mutableStateOf("home") }

    // Setup a listener to update the current route
    LaunchedEffect(controller) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route ?: "home"
        }
    }

    Scaffold(
        floatingActionButton = {
            if (currentRoute.value == "home") {
                FloatingActionButton(
                    onClick = { controller.navigate("add") },
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "add")
                }
            }
        },
    ) { paddingValue ->
        NavHost(navController = controller, startDestination = "home") {
            composable("home") {
                HomeScreen(modifier = Modifier.padding(paddingValue), viewModel, controller)
            }
            composable("add") {
                CrearProducto(controller = controller, viewModel = viewModel)
            }
            composable("detalle") {
                DetalleProducto(controller, viewModel.productoDetalle())
            }
            composable("Camara") {
                CameraScreen(
                    viewModel = viewModel,
                    navController = controller,
                )
            }
            composable("indicarUbi") {
                MapScreen(
                    viewModel = viewModel,
                    navController = controller,
                )
            }
            composable("mostrarUbicacion") {
                MapProviderScreen(
                    viewModel = viewModel,
                    navController = controller,
                )
            }
            composable("agregarStock") {
                val agregarStockViewModel: AgregarStockViewModel = hiltViewModel()
                AddStockScreen(controller, agregarStockViewModel)
            }
            composable("balance") {
                BalanceScreen()
            }
            composable("configuracion") {
                Configuracion(controller)
            }
            composable("listaVenta") {
                ListaProductosVenta(controller = controller, viewModel = viewModel)
            }
            composable("agregarProductoVenta") {
                AgregarProductoVenta(controller = controller, viewModel = viewModel)
            }
            composable("qr") {
                QRScannerScreen(controller, viewModel)
            }
        }
    }
}
