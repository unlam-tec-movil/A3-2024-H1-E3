package ar.edu.unlam.mobile.scaffolding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.SplashViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.WelcomeViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.crearProducto.CrearProductoViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.myHome.MyHomeViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock.AgregarStockViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock.VenderProductosViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.DetalleProducto
import ar.edu.unlam.mobile.scaffolding.ui.screens.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.addStock.AddStockScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.camera.CameraScreen
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
        val splashViewModel by viewModels<SplashViewModel>()
        val welcomeViewModel by viewModels<WelcomeViewModel>()
        val myHomeViewModel by viewModels<MyHomeViewModel>()
        val crearProductoViewModel by viewModels<CrearProductoViewModel>()
        val agregarStockViewModel by viewModels<AgregarStockViewModel>()
        val venderProductosViewModel by viewModels<VenderProductosViewModel>()

        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val startDestination = "splash-screen"
                    MyAppNavHost(
                        startDestination = startDestination,
                        splashViewModel = splashViewModel,
                        welcomeViewModel = welcomeViewModel,
                        myHomeViewModel = myHomeViewModel,
                        crearProductoViewModel = crearProductoViewModel,
                        agregarStockViewModel = agregarStockViewModel,
                        venderProductosViewModel = venderProductosViewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    startDestination: String,
    splashViewModel: SplashViewModel,
    welcomeViewModel: WelcomeViewModel,
    myHomeViewModel: MyHomeViewModel,
    crearProductoViewModel: CrearProductoViewModel,
    agregarStockViewModel: AgregarStockViewModel,
    venderProductosViewModel: VenderProductosViewModel,
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
                splashViewModel = splashViewModel,
            )
        }
        composable("welcome-screen") {
            WelcomeScreen(
                onNavigateToHomeScreen = {
                    navController.navigate("main-screen")
                },
                welcomeViewModel = welcomeViewModel,
            )
        }
        composable("main-screen") {
            MainScreen(
                myHomeViewModel,
                crearProductoViewModel,
                agregarStockViewModel,
                venderProductosViewModel,
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    myHomeViewModel: MyHomeViewModel,
    crearProductoViewModel: CrearProductoViewModel,
    agregarStockViewModel: AgregarStockViewModel,
    venderProductosViewModel: VenderProductosViewModel,
) {
    val controller = rememberNavController()
    val currentRoute = remember { mutableStateOf("home") }

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
                HomeScreen(modifier = Modifier.padding(paddingValue), myHomeViewModel, controller)
            }
            composable("add") {
                CrearProducto(controller = controller, crearProductoViewModel)
            }
            composable("detalle") {
                DetalleProducto(controller, myHomeViewModel.producto)
            }
            composable("Camara") {
                CameraScreen(
                    viewModel = crearProductoViewModel,
                    navController = controller,
                )
            }
            composable("indicarUbi") {
                MapScreen(
                    viewModel = crearProductoViewModel,
                    navController = controller,
                )
            }
            composable("mostrarUbicacion") {
                MapProviderScreen(
                    myHomeViewModel.producto!!.ubicacionProveedor,
                    navController = controller,
                )
            }
            composable("agregarStock") {
                AddStockScreen(controller, agregarStockViewModel)
            }
            composable("listaVenta") {
                ListaProductosVenta(controller = controller, venderProductosViewModel)
            }
            composable("agregarProductoVenta") {
                AgregarProductoVenta(controller = controller, venderProductosViewModel)
            }
            composable("qr") {
                QRScannerScreen(controller, agregarStockViewModel, venderProductosViewModel)
            }
        }
    }
}
