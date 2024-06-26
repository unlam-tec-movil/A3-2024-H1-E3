package ar.edu.unlam.mobile.scaffolding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.crearproducto.CrearProducto
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class CrearProductoTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun crearProductoPantallaFuncionaCorrectamente() {
        val mockViewModel = mock(ProductoViewModel::class.java)
        val navController = mock(NavHostController::class.java)

        composeTestRule.setContent {
            CrearProducto(controller = navController, viewModel = mockViewModel)
        }

        // Verificar que los campos se muestren
        composeTestRule.onNodeWithText("Nombre:").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Precio:").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Adicional").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Stock").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Categoria").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Codigo QR").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Proveedor").assertExists().assertIsDisplayed()

        // Ingresar datos en los campos de texto
        composeTestRule.onNodeWithText("Nombre:").performTextInput("Producto Test")
        composeTestRule.onNodeWithText("Precio:").performTextInput("100")
        composeTestRule.onNodeWithText("Stock").performTextInput("10")
        composeTestRule.onNodeWithText("Categoria").performTextInput("Categoría Test")
        composeTestRule.onNodeWithText("Codigo QR").performTextInput("QR12345")
        composeTestRule.onNodeWithText("Proveedor").performTextInput("Proveedor Test")

        // Verificar que el botón "Indicar ubicación del proveedor" funcione
        composeTestRule.onNodeWithText("Indicar ubicación del proveedor").performClick()
        // Asegurarse de que navega a la pantalla correcta

        // Verificar que el botón "Agregar foto" funcione
        composeTestRule.onNodeWithText("Agregar foto").performClick()
        // Asegurarse de que navega a la pantalla correcta

        // Verificar que el botón "AGREGAR" funcione
        composeTestRule.onNodeWithText("AGREGAR").performClick()
        // Asegurarse de que los datos se guardan y navega a la pantalla principal
    }
}
