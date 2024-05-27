package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.ProductoViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.CrearProducto.CrearProducto
import ar.edu.unlam.mobile.scaffolding.ui.screens.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.splash.SplashScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.welcome.WelcomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(this,InventoryDatabase::class.java,"producto_db").build()
        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val startDestination = "splash-screen"
                    MyAppNavHost(startDestination = startDestination, db.)
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    startDestination: String,
    productos: ProductoViewModel,
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
        composable("main-screen") { MainScreen(productos.getProducto()) }
    }
}

@Composable
fun MainScreen(productos: List<Producto>) {
    // Controller es el elemento que nos permite navegar entre pantallas. Tiene las acciones
    // para navegar como naviegate y también la información de en dónde se "encuentra" el usuario
    // a través del back stack

    val controller = rememberNavController()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { controller.navigate("add") },
                modifier = Modifier.size(64.dp),
                shape = RoundedCornerShape(percent = 50),
                Color.DarkGray,
            ) {
                Icon(Icons.Filled.Add, contentDescription = "add")
            }
        },
    ) { paddingValue ->
        // NavHost es el componente que funciona como contenedor de los otros componentes que
        // podrán ser destinos de navegación.
        NavHost(navController = controller, startDestination = "home") {
            // composable es el componente que se usa para definir un destino de navegación.
            // Por parámetro recibe la ruta que se utilizará para navegar a dicho destino.
            composable("home") {
                // Home es el componente en sí que es el destino de navegación.
                HomeScreen(modifier = Modifier.padding(paddingValue), productos)
            }
            composable("add") {
                // Home es el componente en sí que es el destino de navegación.
                CrearProducto(controller)
            }
        }
    }
}
